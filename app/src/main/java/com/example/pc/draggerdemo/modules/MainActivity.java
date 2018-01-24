package com.example.pc.draggerdemo.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.pc.draggerdemo.R;
import com.example.pc.draggerdemo.base.BaseActivity;
import com.example.pc.draggerdemo.di.component.ApplicationComponent;
import com.example.pc.draggerdemo.di.component.DaggerNewsComponent;
import com.example.pc.draggerdemo.di.module.NewsModule;
import com.example.pc.draggerdemo.mvp.model.NewsResponse;
import com.example.pc.draggerdemo.mvp.presenter.MainPresenter;
import com.example.pc.draggerdemo.mvp.view.IMainView;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainView {

    @Inject
    protected MainPresenter mPresenter;
    private MainViewAdapter mainViewAdapter;

    @BindView(R.id.activity_main_rv)
    RecyclerView mRecyclerView;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initlizeAdapter();
        initilizePresenter();

    }

    private void initlizeAdapter() {
        mainViewAdapter = new MainViewAdapter(this, getApplicationContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mainViewAdapter);

    }

    private void initilizePresenter() {
        mPresenter.getNews();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    protected void resolveDependencies() {
        DaggerNewsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .newsModule(new NewsModule((IMainView) this))
                .build()
                .inject(this);
    }

    @Override
    public void onShowProgressDialog(String s) {
        showProgressDialog(s);
    }

    @Override
    public void onHideProgresDialog() {
        hideProgressDialog();

    }

    @Override
    public void onShowToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoadView(NewsResponse newsResponse) {
        if (newsResponse != null) {
            mainViewAdapter.addAll(newsResponse.getContent());
        }

    }
}
