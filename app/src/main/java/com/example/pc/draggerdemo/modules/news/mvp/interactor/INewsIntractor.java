package com.example.pc.draggerdemo.modules.news.mvp.interactor;

import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;

import java.util.ArrayList;

/**
 * Created by PC on 5/5/2018.
 */

public interface INewsIntractor {
    void saveNews(ArrayList<NewsResponseContent> content);
    ArrayList<NewsResponseContent> getAllNews();
}
