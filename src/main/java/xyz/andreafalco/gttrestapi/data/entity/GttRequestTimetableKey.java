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
public class GttRequestTimetableKey implements Serializable {

    private String requestId;
    private String timetableId;

}
