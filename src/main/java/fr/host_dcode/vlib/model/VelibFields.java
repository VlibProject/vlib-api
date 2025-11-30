package fr.host_dcode.vlib.model;

import java.time.ZonedDateTime;
import java.util.List;


public class VelibFields {

    private String recordId;
    private String name;
    private String station_code;
    private String city;
    private List<Double> coordonnees_geo;
    private ZonedDateTime duedate;


    public String getName(){
        return this.name;
    }
    public String getStation_code(){
        return this.station_code;
    }

    public String getDuedate(){
        return this.duedate.toString();
    }

    public List<Double> getCoordonnees_geo(){
        return this.coordonnees_geo;
    }


}