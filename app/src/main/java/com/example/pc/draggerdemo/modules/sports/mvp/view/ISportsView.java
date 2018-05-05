package com.example.pc.draggerdemo.modules.sports.mvp.view;

import com.example.pc.draggerdemo.base.BaseView;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponse;
import com.example.pc.draggerdemo.modules.sports.mvp.model.SportsResponse;

/**
 * Created by PC on 4/29/2018.
 */

public interface ISportsView extends BaseView {

    void onShowProgressDialog(String s);

    void onHideProgresDialog();

    void onShowToast(String s);

    void onLoadView(SportsResponse newsResponse);
}
