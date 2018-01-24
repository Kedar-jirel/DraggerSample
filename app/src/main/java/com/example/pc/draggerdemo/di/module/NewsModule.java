package com.example.pc.draggerdemo.di.module;

import android.net.Network;

import com.example.pc.draggerdemo.api.NetworkInterface;
import com.example.pc.draggerdemo.di.scope.PerActivity;
import com.example.pc.draggerdemo.mvp.view.IMainView;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by PC on 12/31/2017.
 */

@Module
public class NewsModule {

    protected IMainView mainView;


    @Inject
    public NewsModule(IMainView mainView) {
        this.mainView = mainView;
    }

    @PerActivity
    @Provides
    NetworkInterface providesApi(Retrofit retrofit) {
        return retrofit.create(NetworkInterface.class);
    }

    @PerActivity
    @Provides
    IMainView providesMainView() {
        return mainView;
    }
}
