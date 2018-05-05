package com.example.pc.draggerdemo.base.di.module;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PC on 12/31/2017.
 */

@Module
public class ApplicationModule {


    private String mBaseUrl = null;
    Context context;

    public ApplicationModule(String mBaseUrl, Context context) {
        this.mBaseUrl = mBaseUrl;
        this.context = context;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    GsonConverterFactory provodeGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }
}
