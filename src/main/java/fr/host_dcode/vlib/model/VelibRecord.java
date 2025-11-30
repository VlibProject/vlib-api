package fr.host_dcode.vlib.model;

public class VelibRecord {

    private VelibFields fields;
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