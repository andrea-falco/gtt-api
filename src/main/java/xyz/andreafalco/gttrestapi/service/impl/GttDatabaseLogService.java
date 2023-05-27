package xyz.andreafalco.gttrestapi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.andreafalco.gttrestapi.data.dao.GttDao;
import xyz.andreafalco.gttrestapi.data.entity.*;
import xyz.andreafalco.gttrestapi.model.dto.StopTimetables;
import xyz.andreafalco.gttrestapi.model.mapper.GttMapper;
import xyz.andreafalco.gttrestapi.service.GttLogService;

@Slf4j
@Service
public class GttDatabaseLogService implements GttLogService {

    private final GttMapper gttMapper;
    private final GttDao gttDao;

    public GttDatabaseLogService(GttMapper gttMapper, GttDao gttDao) {
        this.gttMapper = gttMapper;
        this.gttDao = gttDao;
    }

    @Override
    public void gttLog(StopTimetables gtt) {

        // Create and persist request entity
        GttRequest eRequest = new GttRequest();
        gttDao.save(eRequest);

        // Create and persist stop entity
        GttStop eStop = gttMapper.dtoToEntity(gtt.getStop());
        gttDao.save(eStop);

        // Associate request and stop
        GttRequestStop eRequestStop = new GttRequestStop(eRequest.getId(), eStop.getId());
        gttDao.save(eRequestStop);

        // Loop each timetable lines
        gtt.getTimetables().forEach(tt -> {

            // Create and persist line entity
            GttLine eLine = gttMapper.dtoToEntity(tt.getLine());
            gttDao.save(eLine);

            // Create and persist timetable entity
            GttTimetable eTimetable = new GttTimetable(eLine.getId());
            gttDao.save(eTimetable);

            // Associate request and timetable
            GttRequestTimetable eRequestTimetable = new GttRequestTimetable(eRequest.getId(), eTimetable.getId());
            gttDao.save(eRequestTimetable);

            tt.getTimes().forEach(t -> {

                // Create and persist time entity
                GttTime eTime = gttMapper.dtoToEntity(t);
                gttDao.save(eTime);

                // Associate timetable and time
                GttTimetableTime eTimetableTime = new GttTimetableTime(eTimetable.getId(), eTime.getId());
                gttDao.save(eTimetableTime);
            });
        });
    }

}
