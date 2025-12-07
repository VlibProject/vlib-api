package fr.host_dcode.vlib.controller;


import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.service.StationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import java.util.List;

@RequestMapping("/api/v1/station")
@RestController
public class StationController {
 
    private final StationService stationService;
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("/")
    public Page<Station> getAllStations(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        return stationService.getAll(pageable);
    }


    @PutMapping("/update/{id}") 
    public Station updateStation(@RequestBody Station station, @PathVariable("id") String id){
        return stationService.updateStation(station, id);
    }

}
