package fr.host_dcode.vlib.repository;

import fr.host_dcode.vlib.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface StationRepository extends JpaRepository<Station, String> {

    Station findByStationCode(String station_code);



@Query("SELECT s FROM Station s WHERE " +
       "(:name IS NULL OR s.name LIKE CONCAT('%', CAST(:name AS string), '%')) AND " + 
       "(:city IS NULL OR s.city LIKE CONCAT('%', CAST(:city AS string), '%')) AND " +
       "(:station_code IS NULL OR s.stationCode LIKE CONCAT('%', CAST(:station_code AS string), '%'))")
List<Station> findByCriteria(@Param("name") String name, @Param("city") String city, @Param("station_code") String station_code);

}
