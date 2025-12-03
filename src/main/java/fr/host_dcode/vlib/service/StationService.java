package fr.host_dcode.vlib.service;

import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAll(){
        return stationRepository.findAll();
    }

    public List<Station> searchByCriteria(String name, String city){
        return stationRepository.findByCriteria(name, city);
    }


}
