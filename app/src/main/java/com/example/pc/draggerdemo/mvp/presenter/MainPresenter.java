package com.example.pc.draggerdemo.mvp.presenter;

import android.util.Log;

import com.example.pc.draggerdemo.api.NetworkInterface;
import com.example.pc.draggerdemo.base.BasePresenter;
import com.example.pc.draggerdemo.mvp.model.NewsResponse;
import com.example.pc.draggerdemo.mvp.view.IMainView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by PC on 1/2/2018.
 */

public class MainPresenter extends BasePresenter<IMainView> implements Observer<NewsResponse> {

    @Inject
    NetworkInterface mNetworkInterface;

    @Inject
    public MainPresenter() {
    }


    public void getNews() {
        Log.e("sadfsaf", "safasfasdfsa");
        getMainView().onShowProgressDialog("Loading Comlete....");
        Observable<NewsResponse> cakesResponseObservable = mNetworkInterface.getNewsResponse();
        subscribe(cakesResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getMainView().onHideProgresDialog();
        getMainView().onShowToast("Loading Completed....");
    }

    @Override
    public void onError(Throwable e) {
        getMainView().onHideProgresDialog();
        getMainView().onShowToast(e.getMessage());

    }

    @Override
    public void onNext(NewsResponse newsResponse) {
        if(newsResponse!=null){
            getMainView().onLoadView(newsResponse);
        }else {
            getMainView().onShowToast("Null Response fram the server");
        }


    }
}
