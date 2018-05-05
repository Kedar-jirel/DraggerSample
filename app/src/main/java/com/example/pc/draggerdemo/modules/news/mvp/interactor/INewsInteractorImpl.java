package com.example.pc.draggerdemo.modules.news.mvp.interactor;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.pc.draggerdemo.modules.news.mvp.interactor.data.NewsViewModel;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContentData;
import com.example.pc.draggerdemo.modules.news.mvp.view.INewsView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 5/5/2018.
 */

public class INewsInteractorImpl implements INewsIntractor {
    NewsViewModel newsViewModel;
    INewsView iNewsView;
    ArrayList<NewsResponseContent> newsResponseContentArrayList = new ArrayList<>();
    private Object headerData;

    public INewsInteractorImpl(INewsView mainView) {
        this.iNewsView = mainView;
        newsViewModel = ViewModelProviders.of(((Fragment) mainView).getActivity()).get(NewsViewModel.class);
    }


    @Override
    public void saveNews(ArrayList<NewsResponseContent> content) {
//        newsViewModel.deleletNews();
        newsViewModel.saveNews(content);
    }

    @Override
    public ArrayList<NewsResponseContent> getAllNews() {
//        getHeaderData();
        return newsResponseContentArrayList;
    }

    public void getHeaderData() {
        newsViewModel.getNewsContent().observe(((Fragment) iNewsView).getActivity(), new Observer<List<NewsResponseContent>>() {
            @Override
            public void onChanged(@Nullable final List<NewsResponseContent> contactDetails) {
                getChildData(contactDetails);
            }
        });
    }

    private void getChildData(List<NewsResponseContent> contactDetails) {
        for (final NewsResponseContent newsResponseContent : contactDetails) {

            newsViewModel.getChildNewsContent(newsResponseContent.getID()).observe(((Fragment) iNewsView).getActivity(), new Observer<NewsResponseContentData>() {
                @Override
                public void onChanged(@Nullable final NewsResponseContentData contactDetails) {
                    newsResponseContent.setData(contactDetails);
                    newsResponseContentArrayList.add(newsResponseContent);
                }
            });
        }
        Log.e("sfdsa", new Gson().toJson(newsResponseContentArrayList));
    }
}
