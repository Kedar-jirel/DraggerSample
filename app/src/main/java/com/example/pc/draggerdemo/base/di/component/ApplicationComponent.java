package com.example.pc.draggerdemo.base.di.component;

import android.content.Context;

import com.example.pc.draggerdemo.base.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by PC on 12/31/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Retrofit exposeRetrofit();
    Context exposeContext();
}
