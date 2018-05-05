package com.example.pc.draggerdemo.modules.news.di.module;

import com.example.pc.draggerdemo.api.NetworkInterface;
import com.example.pc.draggerdemo.base.di.scope.PerActivity;
import com.example.pc.draggerdemo.modules.news.mvp.view.INewsView;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by PC on 12/31/2017.
 */

@Module
public class NewsModule {

    protected INewsView mainView;


    @Inject
    public NewsModule(INewsView mainView) {
        this.mainView = mainView;
    }

    @PerActivity
    @Provides
    NetworkInterface providesApi(Retrofit retrofit) {
        return retrofit.create(NetworkInterface.class);
    }

    @PerActivity
    @Provides
    INewsView providesMainView() {
        return mainView;
    }
}
