package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class GttRequest extends UuidEntity {

    private LocalDateTime timestamp;

    public GttRequest() {
        this.timestamp = LocalDateTime.now();
    }

}
