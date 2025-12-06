package fr.host_dcode.vlib.controller;


import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.repository.StationRepository;
import fr.host_dcode.vlib.service.StationService;
import org.springframework.web.bind.annotation.*;

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
        return stationService.getAll();
    }


    @PutMapping("/update/{id}")
    public Station updateStation(@RequestBody Station station, @PathVariable("id") String id){
        return stationService.updateStation(station, id);
    }

}
