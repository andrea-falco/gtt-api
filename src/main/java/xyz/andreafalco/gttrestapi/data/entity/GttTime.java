package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class GttTime extends UuidEntity {

    private LocalTime time;
    private Boolean realtime;
    private Boolean accessible;

}
