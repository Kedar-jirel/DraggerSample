package com.example.pc.draggerdemo.modules.news.mvp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "News_Header_Details")
public class NewsResponseContent implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int ID;

    @Ignore
    private NewsResponseContentData data;

    @ColumnInfo(name = "CONTENT_TYPE")
    private String mType;

    public NewsResponseContent() {
    }

    @Ignore
    public NewsResponseContent(int ID, NewsResponseContentData data, String mType) {
        this.ID = ID;
        this.data = data;
        this.mType = mType;
    }

    public NewsResponseContentData getData() {
        return this.data;
    }

    public void setData(NewsResponseContentData data) {
        this.data = data;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }
}
