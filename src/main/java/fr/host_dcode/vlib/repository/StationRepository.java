package fr.host_dcode.vlib.repository;

import fr.host_dcode.vlib.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {

    Station findByStationCode(String station_code);

}
