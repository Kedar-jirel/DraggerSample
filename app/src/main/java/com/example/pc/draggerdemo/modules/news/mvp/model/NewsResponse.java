package com.example.pc.draggerdemo.modules.news.mvp.model;

import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;

import java.util.ArrayList;

public class NewsResponse {
    private String message;
    private ArrayList<NewsResponseContent> content;
    private String status;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<NewsResponseContent> getContent() {
        return this.content;
    }

    public void setContent(ArrayList<NewsResponseContent> content) {
        this.content = content;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
