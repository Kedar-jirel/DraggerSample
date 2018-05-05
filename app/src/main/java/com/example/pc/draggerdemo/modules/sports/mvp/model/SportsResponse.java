package com.example.pc.draggerdemo.modules.sports.mvp.model;

import java.util.ArrayList;

/**
 * Created by PC on 4/29/2018.
 */

public class SportsResponse {
    private String message;
    private ArrayList<SportsResponseContent> content;
    private String status;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<SportsResponseContent> getContent() {
        return this.content;
    }

    public void setContent(ArrayList<SportsResponseContent> content) {
        this.content = content;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
