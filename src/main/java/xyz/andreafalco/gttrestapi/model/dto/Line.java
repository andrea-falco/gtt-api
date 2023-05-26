package xyz.andreafalco.gttrestapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Line {

    /**
     * Line number
     */
    private String number;

    /**
     * Line destination
     */
    private String destination;

}
