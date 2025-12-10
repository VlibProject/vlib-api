package fr.host_dcode.vlib.controller;
import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/search/")
    public List<Station> searchByCriteria(
        @RequestParam(required = false) String name, 
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String station_code){
            return stationService.searchByCriteria(name, city, station_code); 
    }
    @PutMapping("/update/{id}")
    public Station updateStation(@RequestBody Station station, @PathVariable("id") String id){
        return stationService.updateStation(station, id);
    }

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable("id") String id) {
        return stationService.getStationById(id);
    }

}
