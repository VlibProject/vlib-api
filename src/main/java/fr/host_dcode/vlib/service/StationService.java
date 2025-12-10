package fr.host_dcode.vlib.service;

import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.repository.StationRepository;
import fr.host_dcode.vlib.service.RealTimeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;
    private final RealTimeService realTimeService;

    public StationService(StationRepository stationRepository, RealTimeService realTimeService) {
        this.stationRepository = stationRepository;
        this.realTimeService = realTimeService;
    }

    public List<Station> getAll() {
        return stationRepository.findAll();
    }

    public List<Station> searchByCriteria(String name, String city, String station_code) {
        return stationRepository.findByCriteria(name, city, station_code);
    }

    public Station updateStation(Station station, String id) {
        if (station == null) {
            throw new IllegalArgumentException("Station object cannot be null");
        }
        Station existingStation = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Station non trouvée avec l'id : " + id));
        if (station.getName() != null) {
            existingStation.setName(station.getName());
        }

        if (station.getDescription() != null) {
            existingStation.setDescription(station.getDescription());
        }
        return stationRepository.save(existingStation);
    }

    public Station getStationById(String id, boolean realTime) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station non trouvée avec l'id : " + id));

        if (realTime) {
            realTimeService.realTimeData(station);
        }

        return station;
    }
}
