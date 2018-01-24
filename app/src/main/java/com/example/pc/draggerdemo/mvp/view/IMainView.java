package com.example.pc.draggerdemo.mvp.view;

import com.example.pc.draggerdemo.mvp.model.NewsResponse;

/**
 * Created by PC on 1/2/2018.
 */

public interface IMainView extends BaseView{

    void onShowProgressDialog(String s);

    void onHideProgresDialog();

    void onShowToast(String s);

    void onLoadView(NewsResponse newsResponse);

}
