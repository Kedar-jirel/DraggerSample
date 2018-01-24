package com.example.pc.draggerdemo.base;

import com.example.pc.draggerdemo.mvp.view.BaseView;
import com.example.pc.draggerdemo.mvp.view.IMainView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC on 1/2/2018.
 */

public class BasePresenter<V extends BaseView> {

    @Inject
    protected V mainView;

    protected V getMainView() {
        return mainView;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
