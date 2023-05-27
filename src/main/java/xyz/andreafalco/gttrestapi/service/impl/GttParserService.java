package xyz.andreafalco.gttrestapi.service.impl;

import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import xyz.andreafalco.gttrestapi.model.dto.*;
import xyz.andreafalco.gttrestapi.service.GttService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Setter
@Service
@ConfigurationProperties("gtt.parser")
public class GttParserService implements GttService {

    private RequestConfig request;
    private ParserConfig query;

    public StopTimetables gtt(String stop) {

        log.debug("Getting stop timetables for stop[{}]", stop);

        try {
            // Get GTT html page
            Document gtt = Jsoup.connect(request.getUrl())
                    .data(request.getQueryParameter(), String.valueOf(stop))
                    .get();

            // Get stop info
            Stop s = getStop(gtt);

            if (Objects.isNull(s))
                return null;

            // Get timetables info
            List<Timetable> t = getTimetables(gtt);

            StopTimetables st = new StopTimetables(s, t);
            log.debug("Got stop timetables for stop[{}]: {}", stop, st);

            return st;

        } catch (Exception e) {
            log.error("Error getting stop timetables!", e);
            return null;
        }
    }

    private Stop getStop(Document gtt) {

        Element stopElement = gtt.selectFirst(query.getStopElement());
        Element stopParentElement = Objects.nonNull(stopElement) ? stopElement.parent() : null;

        log.trace("stopElement[{}] stopParentElement[{}]", stopElement, stopParentElement);

        if (Objects.isNull(stopElement) || Objects.isNull(stopParentElement))
            return null;

        // Get stop element text (i.e. "40 - PORTA NUOVA")
        Element stopNameElement = stopParentElement.selectFirst(query.getStopName());

        log.trace("stopNameElement[{}]", stopNameElement);

        if (Objects.isNull(stopNameElement))
            return null;

        String stopString = stopNameElement.text();

        log.trace("stopString[{}]", stopString);

        // Get stop number and name
        Pattern stopPattern = Pattern.compile(query.getStopRegex());
        Matcher stopMatcher = stopPattern.matcher(stopString);

        if (!stopMatcher.find())
            return null;

        String stopNumber = stopMatcher.group(1); // "40"
        String stopName = stopMatcher.group(2); // "PORTA NUOVA"

        log.trace("stopNumber[{}] stopName[{}]", stopNumber, stopName);

        // Get stop area
        Element stopAreaElement = stopParentElement.selectFirst(query.getStopAreaElement());

        log.trace("stopAreaElement[{}]", stopString);

        if (Objects.isNull(stopAreaElement))
            return null;

        String stopArea = stopAreaElement.text().trim();

        log.trace("stopArea[{}]", stopString);

        // Get if stop is accessible or not
        Element accessibleElement = stopParentElement.selectFirst(query.getStopAccessibleElement());
        Boolean accessible =
                Objects.nonNull(accessibleElement) &&
                        accessibleElement
                                .attr(query.getStopAccessibleAttribute())
                                .contains(query.getStopAccessibleAttributeKeyword());

        log.trace("accessibleElement[{}] accessible[{}]", accessibleElement, accessible);

        Stop stop = new Stop(
                stopNumber,
                stopName,
                stopArea,
                accessible
        );

        log.trace("stop[{}]", accessibleElement);

        return stop;
    }

    private List<Timetable> getTimetables(Document gtt) {

        List<Timetable> timetables = new ArrayList<>();

        // Get rows of times table
        Elements rows = gtt.select(query.getTableRows());
        
        log.trace("rows[{}]", rows);

        for (Element r : rows) {

            Timetable tt = new Timetable();

            // Get line
            try {
                String lineNumber = r.selectFirst(query.getLineNumber()).text();
                String lineDestination = r.select(query.getLineDestination()).get(1).text();

                log.trace("lineNumber[{}] lineDestination[{}]", lineNumber, lineDestination);

                Line l = new Line(lineNumber, lineDestination);
                tt.setLine(l);

            } catch (Exception e) {
                log.trace("Error parsing line info!", e);
                continue;
            }

            // Check if there are scheduled times, this means that there are no realtime times
            Element scheduledString = r.selectFirst(query.getScheduledTimes());

            log.trace("scheduledString[{}]", scheduledString);

            if (scheduledString != null) {
                String[] times = scheduledString.text().split(query.getTimesSplit());
                for (String t : times) {
                    if (!t.isEmpty()) {
                        Time time = new Time(LocalTime.parse(t, DateTimeFormatter.ISO_LOCAL_TIME));
                        tt.addTime(time);
                    }
                }
            } else {
                Elements timeSpan = r.select(query.getRealtimeTimes());

                log.trace("timeSpan[{}]", timeSpan);

                for (Element t : timeSpan) {
                    String timeString = t.text();
                    if (!timeString.isEmpty()) {
                        boolean accessible = query.getAccessibleAttrValue().equals(t.attr(query.getAccessibleAttrName()));
                        Time time = new Time(
                                LocalTime.parse(timeString.replace(query.getRealtimeChar(), Strings.EMPTY), DateTimeFormatter.ISO_LOCAL_TIME),
                                timeString.contains(query.getRealtimeChar()),
                                accessible
                        );
                        tt.addTime(time);
                    }

                }
            }

            // If the table has only 1 empty rows there is no bus/tram coming, so an empty timetables will be returned
            if (rows.size() == 1 && timetableIsEmpty(tt)) {
                return timetables;
            }

            timetables.add(tt);
        }

        return timetables;
    }

    private boolean timetableIsEmpty(Timetable timetable) {
        return Objects.isNull(timetable)
                || Objects.isNull(timetable.getLine())
                || Objects.isNull(timetable.getLine().getNumber())
                || Objects.isNull(timetable.getLine().getDestination());
    }

    @Data
    private static class RequestConfig {
        private String url;
        private String queryParameter;
    }

    @Data
    private static class ParserConfig {
        private String stopElement;
        private String stopName;
        private String stopRegex;
        private String stopAreaElement;
        private String stopAccessibleElement;
        private String stopAccessibleAttribute;
        private String stopAccessibleAttributeKeyword;
        private String tableRows;
        private String lineNumber;
        private String lineDestination;
        private String scheduledTimes;
        private String realtimeTimes;
        private String realtimeChar;
        private String timesSplit;
        private String accessibleAttrName;
        private String accessibleAttrValue;
    }

}
