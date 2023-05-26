package xyz.andreafalco.gttrestapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Time {

    /**
     * Arrival time
     */
    private LocalTime time;

    /**
     * Indicates if the time is real (not fixed schedule)
     */
    private Boolean realtime;

    /**
     * Indicates if the bus/tram is accessible
     */
    private Boolean accessible;

    public Time(LocalTime time) {
        this.time = time;
        this.realtime = Boolean.FALSE;
        this.accessible = Boolean.FALSE;
    }

}
