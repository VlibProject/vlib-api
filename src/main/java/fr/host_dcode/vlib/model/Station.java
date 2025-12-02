package fr.host_dcode.vlib.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "recordid")
    private String recordId;
    private String name;
    @Column(name = "station_code")
    private String stationCode;
    private double latitude;
    private double longitude;
    private String city;
    private String description;
    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


    public Station(){}

    public Station(String name, String recordId, String stationCode,String city, double latitude, double longitude, String description) {
        this.name = name;
        this.recordId = recordId;
        this.stationCode = stationCode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public String getStationCode(){
        return this.stationCode;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getRecordId(){
        return this.recordId;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public Double getLatitude(){
        return this.latitude;
    }
    public Double getLongitude(){
        return this.longitude;
    }

    public String getCity(){
        return this.city;
    }
    public void setCity(String city){}


}
