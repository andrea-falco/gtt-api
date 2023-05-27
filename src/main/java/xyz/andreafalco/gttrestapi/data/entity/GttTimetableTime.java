package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class GttTimetableTime {

    @EmbeddedId
    private GttTimetableTimeKey key;

    public GttTimetableTime(String timetableId, String timeId) {
        this.key = new GttTimetableTimeKey(timetableId, timeId);
    }

}
