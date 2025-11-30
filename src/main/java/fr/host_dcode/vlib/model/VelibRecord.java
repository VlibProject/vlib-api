package fr.host_dcode.vlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VelibRecord {

    private VelibFields fields;
    @JsonProperty("recordid")
    public String recordId;

    public VelibFields getFields() {
        return fields;
    }

    public void setFields(VelibFields fields) {
        this.fields = fields;
    }

    public String getRecordId(){
        return this.recordId;
    }

    public void setRecordId(String recordid) {
        this.recordId = recordid;
    }

}