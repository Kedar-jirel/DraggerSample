package com.example.pc.draggerdemo.modules.sports.di.module;

import com.example.pc.draggerdemo.api.NetworkInterface;
import com.example.pc.draggerdemo.base.di.scope.PerActivity;
import com.example.pc.draggerdemo.modules.news.mvp.view.INewsView;
import com.example.pc.draggerdemo.modules.sports.mvp.view.ISportsView;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by PC on 4/28/2018.
 */

@Module
public class SportModule {

    protected ISportsView mainView;


    @Inject
    public SportModule(ISportsView mainView) {
        this.mainView = mainView;
    }

    @PerActivity
    @Provides
    NetworkInterface providesApi(Retrofit retrofit) {
        return retrofit.create(NetworkInterface.class);
    }


    @PerActivity
    @Provides
    ISportsView providesMainView() {
        return mainView;
    }
}
