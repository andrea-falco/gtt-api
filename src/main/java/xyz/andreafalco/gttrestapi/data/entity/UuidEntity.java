package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class UuidEntity {

    @Id
    private String id;

    public UuidEntity() {
        this.id = UUID.randomUUID().toString();
    }

}
