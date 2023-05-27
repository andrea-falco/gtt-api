package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class GttStop extends UuidEntity {

    private String number;
    private String name;
    private String area;
    private Boolean accessible;

}
