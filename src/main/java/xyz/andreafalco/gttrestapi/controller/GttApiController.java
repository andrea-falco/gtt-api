package xyz.andreafalco.gttrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.andreafalco.gttrestapi.model.dto.StopTimetables;
import xyz.andreafalco.gttrestapi.model.response.StopResponse;
import xyz.andreafalco.gttrestapi.service.GttService;

@RestController
@RequestMapping("gtt")
public class GttApiController {

    private final GttService gttService;

    public GttApiController(GttService gttService) {
        this.gttService = gttService;
    }

    @GetMapping("/{stop}")
    public StopResponse timetablesByStop(@PathVariable String stop) {
        StopTimetables stopTimetables = gttService.gtt(stop);
        return new StopResponse(stopTimetables);
    }

}
