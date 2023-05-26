package xyz.andreafalco.gttrestapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stop {

    /**
     * Stop number (palina, in GTT terms)
     */
    private String number;

    /**
     * Stop name
     */
    private String name;

    /**
     * Stop street address
     */
    private String area;

    /**
     * Indicates if the stop is accessible or not
     */
    private Boolean accessible;

}
