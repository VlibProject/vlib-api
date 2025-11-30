package fr.host_dcode.vlib.model;

import java.util.List;

public class VelibApiResponse {

    private List<VelibRecord> records;

    public List<VelibRecord> getRecords() {
        return records;
    }

    public void setRecords(List<VelibRecord> records) {
        this.records = records;
    }

}