package com.example.pc.draggerdemo.modules.sports.mvp.presenter;


import com.example.pc.draggerdemo.api.NetworkInterface;
import com.example.pc.draggerdemo.base.BasePresenter;
import com.example.pc.draggerdemo.modules.sports.mvp.model.SportsResponse;
import com.example.pc.draggerdemo.modules.sports.mvp.view.ISportsView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by PC on 4/29/2018.
 */

public class SportsPresenter extends BasePresenter<ISportsView> implements Observer<SportsResponse> {


    @Inject
    NetworkInterface mNetworkInterface;
    Observable<SportsResponse> sportsResponseObservable;

    @Inject
    public SportsPresenter() {
    }

    public void getSports() {
        getMainView().onShowProgressDialog("Loading,Please Wait....");
        sportsResponseObservable = mNetworkInterface.getSportResponse();
        subscribe(sportsResponseObservable, this);
    }


    @Override
    public void onCompleted() {
        getMainView().onHideProgresDialog();
        unsubcribe();

    }

    @Override
    public void onError(Throwable e) {
        getMainView().onHideProgresDialog();
        getMainView().onShowToast(e.getMessage());
        unsubcribe();
    }

    @Override
    public void onNext(SportsResponse sportsResponse) {
        getMainView().onHideProgresDialog();
        getMainView().onLoadView(sportsResponse);
    }

    public void unsubscibeSubscription(){
        unsubcribe();
    }

}
