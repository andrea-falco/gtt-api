package xyz.andreafalco.gttrestapi.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GttRequestStop {

    @Id
    private String requestId;
    private String stopId;

}
