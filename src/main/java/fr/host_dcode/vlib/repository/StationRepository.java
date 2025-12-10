package fr.host_dcode.vlib.repository;

import fr.host_dcode.vlib.model.Station;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;



@Repository
public interface StationRepository extends JpaRepository<Station, String> {

    Station findByStationCode(String station_code);


@Query("SELECT s FROM Station s WHERE " +
       "s.name LIKE CONCAT('%', COALESCE(:name, ''), '%') AND " + 
       "s.city LIKE CONCAT('%', COALESCE(:city, ''), '%') AND " +
       "s.stationCode LIKE CONCAT('%', COALESCE(:station_code, ''), '%')")
Page<Station> findByCriteria(@Param("name") String name, @Param("city") String city, @Param("station_code") String station_code, Pageable pageable);
}
