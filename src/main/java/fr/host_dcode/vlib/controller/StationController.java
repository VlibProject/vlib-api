package fr.host_dcode.vlib.controller;


import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/station")
@RestController
public class StationController {

    private final StationService stationService;
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("/")
    public List<Station> getAllStations(){
        return stationService.getAlls();
    }




}
