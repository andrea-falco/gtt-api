package xyz.andreafalco.gttrestapi.service;

import xyz.andreafalco.gttrestapi.model.dto.StopTimetables;

public interface GttService {

    StopTimetables gtt(String stop);

}
