package fr.host_dcode.vlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;


public class VelibFields {

    @JsonProperty("recordid")
    private String recordId;
    private String name;
    @JsonProperty("stationcode")
    private String station_code;
    @JsonProperty("nom_arrondissement_communes")
    private String city;
    private List<Double> coordonnees_geo;
    private ZonedDateTime duedate;


    public String getName(){
        return this.name;
    }
    public String getStationCode(){
        return this.station_code;
    }

    public String getDuedate(){
        return this.duedate != null ? this.duedate.toString() : null;
    }

    public List<Double> getCoordonnees_geo(){
        return this.coordonnees_geo;
    }

    public String getCity(){
        return this.city;
    }


}