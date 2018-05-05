package com.example.pc.draggerdemo.api;

import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponse;
import com.example.pc.draggerdemo.modules.sports.mvp.model.SportsResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by PC on 12/31/2017.
 */

public interface NetworkInterface {
    //    https://api.myjson.com/bins/19sjyz
    @GET("19sjyz")
    Observable<NewsResponse> getNewsResponse();

    @GET("19sjyz")
    Observable<SportsResponse> getSportResponse();
}
