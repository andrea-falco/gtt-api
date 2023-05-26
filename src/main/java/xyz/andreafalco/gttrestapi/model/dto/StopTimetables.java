package xyz.andreafalco.gttrestapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StopTimetables {

    private Stop stop;
    private List<Timetable> timetables;

}
