package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class GttTimetableTimeKey implements Serializable {

    private String timetableId;
    private String timeId;

}
