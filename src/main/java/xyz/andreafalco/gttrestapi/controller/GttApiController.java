package xyz.andreafalco.gttrestapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.andreafalco.gttrestapi.model.dto.StopTimetables;
import xyz.andreafalco.gttrestapi.model.response.StopResponse;
import xyz.andreafalco.gttrestapi.service.GttLogService;
import xyz.andreafalco.gttrestapi.service.GttService;

import java.util.Objects;

@RestController
@RequestMapping("gtt")
public class GttApiController {

    @Value("${gtt.api.log:false}")
    private Boolean log;

    private final GttService gttService;
    private final GttLogService gttLogService;

    public GttApiController(GttService gttService, GttLogService gttLogService) {
        this.gttService = gttService;
        this.gttLogService = gttLogService;
    }

    @GetMapping("/{stop}")
    public StopResponse timetablesByStop(@PathVariable String stop) {

        // Get stop timetables
        StopTimetables stopTimetables = gttService.gtt(stop);

        // Log stop timetables
        if (log && Objects.nonNull(stopTimetables)) {
            gttLogService.gttLog(stopTimetables);
        }

        return new StopResponse(stopTimetables);
    }

}
