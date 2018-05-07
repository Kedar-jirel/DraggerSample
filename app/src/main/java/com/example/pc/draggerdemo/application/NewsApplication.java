package com.example.pc.draggerdemo.application;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.pc.draggerdemo.base.di.component.ApplicationComponent;
import com.example.pc.draggerdemo.base.di.component.DaggerApplicationComponent;
import com.example.pc.draggerdemo.base.di.module.ApplicationModule;
import com.example.pc.draggerdemo.extras.ConnectivityReceiver;

/**
 * Created by PC on 1/2/2018.
 */

public class NewsApplication extends Application {
    ApplicationComponent mApplicationComponent;
    private static NewsApplication mInstance;

    private HttpProxyCacheServer proxy;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencies();
        mInstance = this;
    }

    private void initializeDependencies() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule("https://api.myjson.com/bins/", this))
                .build();
    }


    public static HttpProxyCacheServer getProxy(Context context) {
        NewsApplication app = (NewsApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static synchronized NewsApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
