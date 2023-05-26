package xyz.andreafalco.gttrestapi.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Timetable {

    private Line line;
    private List<Time> times;

    public void addTime(Time time) {
        if (Objects.isNull(this.times)) {
            this.times = new ArrayList<>();
        }
        this.times.add(time);
    }

}
