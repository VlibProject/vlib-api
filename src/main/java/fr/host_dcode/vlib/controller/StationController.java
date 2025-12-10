package fr.host_dcode.vlib.controller;
import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.repository.StationRepository;
import fr.host_dcode.vlib.service.StationService;
import fr.host_dcode.vlib.service.RealTimeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

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
    private final RealTimeService realTimeService;
    private final StationRepository stationRepository;

    public StationController(StationService stationService, RealTimeService realTimeService, StationRepository stationRepository) {
        this.stationService = stationService;
        this.realTimeService = realTimeService;
        this.stationRepository = stationRepository;
    }


    @GetMapping("/")
    public Page<Station> getAllStations(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        return stationService.getAll(pageable);
    }

    @GetMapping("/search/")
    public Page<Station> searchByCriteria(
        @RequestParam(required = false) String name, 
        @RequestParam(required = false) String city,
        @RequestParam(required = false) String station_code,
        @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
            return stationService.searchByCriteria(name, city, station_code, pageable); 
    }

    @PutMapping("/update/{id}")
    public Station updateStation(@RequestBody Station station, @PathVariable("id") String id){
        return stationService.updateStation(station, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(
            @PathVariable("id") String id,
            @RequestParam(value = "realTime", required = false, defaultValue = "false") boolean realTime) {

        Station station = stationService.getStationById(id, realTime);
        return ResponseEntity.ok(station);
    }

}
