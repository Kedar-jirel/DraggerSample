package com.example.pc.draggerdemo.modules.sports.mvp.model;

public class SportsResponseContent implements java.io.Serializable {
    private SportsResponseContentData data;
    private String id;
    private String mType;

    public SportsResponseContentData getData() {
        return this.data;
    }

    public void setData(SportsResponseContentData data) {
        this.data = data;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMType() {
        return this.mType;
    }

    public void setMType(String mType) {
        this.mType = mType;
    }
}
