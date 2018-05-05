package com.example.pc.draggerdemo.modules.news.mvp.interactor.data;
//import android.arch.lifecycle.AndroidViewModel;
//import android.arch.lifecycle.LiveData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.pc.draggerdemo.base.dataprovider.NewsDatabase;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 5/5/2018.
 */

public class NewsViewModel extends AndroidViewModel {

    private  NewsRepository  newsRepository;
    private  LiveData<List<NewsResponseContent>>  newsContent;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        this.newsRepository = new NewsRepository(application);
    }

    public void saveNews(ArrayList<NewsResponseContent> content) {
        newsRepository.saveNews(content);
    }

    public LiveData<List<NewsResponseContent>> getNewsContent() {
        return newsRepository.getAllNews();
    }

    public void deleletNews() {
        newsRepository.deletAllNewsComponent();
    }

    public LiveData<NewsResponseContentData> getChildNewsContent(int id) {
        return newsRepository.getNewsContentById(id);
    }
}
