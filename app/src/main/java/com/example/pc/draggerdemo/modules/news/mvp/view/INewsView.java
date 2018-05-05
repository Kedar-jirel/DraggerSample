package com.example.pc.draggerdemo.modules.news.mvp.view;

import com.example.pc.draggerdemo.base.BaseView;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;

import java.util.ArrayList;

/**
 * Created by PC on 1/2/2018.
 */

public interface INewsView extends BaseView {

    void onShowProgressDialog(String s);

    void onHideProgresDialog();

    void onShowToast(String s);

    void onLoadView(ArrayList<NewsResponseContent> newsResponse);

}
