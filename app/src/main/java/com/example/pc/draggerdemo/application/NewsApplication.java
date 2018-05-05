package com.example.pc.draggerdemo.application;

import android.app.Application;

import com.example.pc.draggerdemo.base.di.component.ApplicationComponent;
import com.example.pc.draggerdemo.base.di.component.DaggerApplicationComponent;
import com.example.pc.draggerdemo.base.di.module.ApplicationModule;

/**
 * Created by PC on 1/2/2018.
 */

public class NewsApplication extends Application {
    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencies();
    }

    private void initializeDependencies() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule("https://api.myjson.com/bins/", this))
                .build();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
