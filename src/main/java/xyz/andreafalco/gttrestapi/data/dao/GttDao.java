package xyz.andreafalco.gttrestapi.data.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import xyz.andreafalco.gttrestapi.data.entity.*;
import xyz.andreafalco.gttrestapi.data.repository.*;

@Slf4j
@Repository
public class GttDao {

    private final GttRequestRepository gttRequestRepository;
    private final GttStopRepository gttStopRepository;
    private final GttRequestStopRepository gttRequestStopRepository;
    private final GttLineRepository gttLineRepository;
    private final GttTimetableRepository gttTimetableRepository;
    private final GttRequestTimetableRepository gttRequestTimetableRepository;
    private final GttTimeRepository gttTimeRepository;
    private final GttTimetableTimeRepository gttTimetableTimeRepository;

    public GttDao(
            GttRequestRepository gttRequestRepository,
            GttStopRepository gttStopRepository,
            GttRequestStopRepository gttRequestStopRepository,
            GttLineRepository gttLineRepository,
            GttTimetableRepository gttTimetableRepository,
            GttRequestTimetableRepository gttRequestTimetableRepository,
            GttTimeRepository gttTimeRepository,
            GttTimetableTimeRepository gttTimetableTimeRepository
    ) {
        this.gttRequestRepository = gttRequestRepository;
        this.gttStopRepository = gttStopRepository;
        this.gttRequestStopRepository = gttRequestStopRepository;
        this.gttLineRepository = gttLineRepository;
        this.gttTimetableRepository = gttTimetableRepository;
        this.gttRequestTimetableRepository = gttRequestTimetableRepository;
        this.gttTimeRepository = gttTimeRepository;
        this.gttTimetableTimeRepository = gttTimetableTimeRepository;
    }

    public GttRequest save(GttRequest request) {
        return gttRequestRepository.save(request);
    }

    public GttStop save(GttStop request) {
        return gttStopRepository.save(request);
    }

    public GttRequestStop save(GttRequestStop request) {
        return gttRequestStopRepository.save(request);
    }

    public GttLine save(GttLine request) {
        return gttLineRepository.save(request);
    }

    public GttTimetable save(GttTimetable request) {
        return gttTimetableRepository.save(request);
    }

    public GttRequestTimetable save(GttRequestTimetable request) {
        return gttRequestTimetableRepository.save(request);
    }

    public GttTime save(GttTime request) {
        return gttTimeRepository.save(request);
    }

    public GttTimetableTime save(GttTimetableTime request) {
        return gttTimetableTimeRepository.save(request);
    }

}
