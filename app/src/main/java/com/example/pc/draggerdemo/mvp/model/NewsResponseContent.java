package com.example.pc.draggerdemo.mvp.model;

public class NewsResponseContent {
    private NewsResponseContentData data;
    private String id;
    private String mType;

    public NewsResponseContentData getData() {
        return this.data;
    }

    public void setData(NewsResponseContentData data) {
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
