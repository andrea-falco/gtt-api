package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class GttLine extends UuidEntity {

    private String number;
    private String destination;

}
