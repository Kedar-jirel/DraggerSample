package com.example.pc.draggerdemo.modules.sports.mvp.model;

public class SportsResponseContentData implements java.io.Serializable {
    private static final long serialVersionUID = 2898276832124512383L;
    private String mUrl;
    private String mTitle;
    private String mDate;
    private String mDescription;

    public String getMUrl() {
        return this.mUrl;
    }

    public void setMUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMDate() {
        return this.mDate;
    }

    public void setMDate(String mDate) {
        this.mDate = mDate;
    }

    public String getMDescription() {
        return this.mDescription;
    }

    public void setMDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
