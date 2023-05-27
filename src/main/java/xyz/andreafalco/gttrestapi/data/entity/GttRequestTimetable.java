package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class GttRequestTimetable {

    @EmbeddedId
    private GttRequestTimetableKey key;

    public GttRequestTimetable(String requestId, String timetableId) {
        this.key = new GttRequestTimetableKey(requestId, timetableId);
    }

}
