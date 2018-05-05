package com.example.pc.draggerdemo.modules.news.mvp.presenter;

import com.example.pc.draggerdemo.api.NetworkInterface;
import com.example.pc.draggerdemo.base.BasePresenter;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponse;
import com.example.pc.draggerdemo.modules.news.mvp.view.INewsView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by PC on 1/2/2018.
 */

public class NewsPresenter extends BasePresenter<INewsView> implements Observer<NewsResponse> {

    @Inject
    NetworkInterface mNetworkInterface;

    @Inject
    public NewsPresenter() {
    }


    public void getNews() {
        Observable<NewsResponse> cakesResponseObservable = mNetworkInterface.getNewsResponse();
        subscribe(cakesResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getMainView().onHideProgresDialog();
    }

    @Override
    public void onError(Throwable e) {
        getMainView().onHideProgresDialog();
        getMainView().onShowToast(e.getMessage());

    }

    @Override
    public void onNext(NewsResponse newsResponse) {
        if (newsResponse != null) {
            getMainView().onLoadView(newsResponse);
        } else {
            getMainView().onShowToast("Null Response fram the server");
        }


    }
}
