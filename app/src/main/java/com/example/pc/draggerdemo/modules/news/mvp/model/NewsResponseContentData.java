package com.example.pc.draggerdemo.modules.news.mvp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;


//@Entity(foreignKeys = @ForeignKey(entity = NewsResponseContent.class,
//        parentColumns = "ID",
//        childColumns = "HeaderId",
//        onDelete = CASCADE))
@Entity(tableName = "NEWS_CONTENT")
public class NewsResponseContentData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    protected int ID;

    @ColumnInfo(name = "HEADER_ID")
    private int HeaderId;
    @ColumnInfo(name = "Url")
    private String mUrl;
    @ColumnInfo(name = "TITLE")
    private String mTitle;
    @ColumnInfo(name = "DATE")
    private String mDate;
    @ColumnInfo(name = "DESCRIPTION")
    private String mDescription;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHeaderId() {
        return HeaderId;
    }

    public void setHeaderId(int headerId) {
        HeaderId = headerId;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
