package com.example.pc.draggerdemo.base;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by PC on 1/2/2018.
 */

public class BasePresenter<V extends BaseView> {

    @Inject
    protected V mainView;

    Subscription  subscription;

    protected V getMainView() {
        return mainView;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
         subscription =observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


    protected<T>  void  unsubcribe(){
        subscription.unsubscribe();
    }

}
