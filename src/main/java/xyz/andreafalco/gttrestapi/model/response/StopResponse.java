package xyz.andreafalco.gttrestapi.model.response;

import lombok.Data;
import xyz.andreafalco.gttrestapi.model.dto.StopTimetables;

import java.util.Objects;

@Data
public class StopResponse {

    private Boolean success;
    private StopTimetables stopTimetables;

    public StopResponse(StopTimetables stopTimetables) {
        this.success = Objects.nonNull(stopTimetables);
        this.stopTimetables = stopTimetables;
    }

}
