package fr.host_dcode.vlib.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String recordId;
    private String name;
    private String station_code;
    private double latitude;
    private double longitude;
    private String address;
    private String city;
    private String description;
    private LocalDateTime last_update;


    public Station(){}

    public Station(String name, String stationCode, double latitude, double longitude, String description) {
        this.name = name;
        this.station_code = stationCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public String getStationCode(){
        return this.station_code;
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

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }


}
