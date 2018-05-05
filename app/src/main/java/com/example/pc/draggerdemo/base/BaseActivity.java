package com.example.pc.draggerdemo.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.draggerdemo.application.NewsApplication;
import com.example.pc.draggerdemo.base.di.component.ApplicationComponent;
import com.example.pc.draggerdemo.extras.ConnectivityReceiver;

import butterknife.ButterKnife;

/**
 * Created by PC on 12/31/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    ProgressDialog mProgressDialog = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
        checkConnection();
    }

    protected abstract int getContentView();


    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        changeFragment();
    }

    protected void changeFragment() {

    }

    protected void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((NewsApplication) getApplication()).getApplicationComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        if (isConnected) {
            showInternetStauts("Welcome,You are online", Color.GREEN);
            reloadView();
        } else {
            showInternetStauts("Sorry, Your device seems to be offline", Color.RED);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        NewsApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    protected abstract void showInternetStauts(String s, int red);

    protected abstract void reloadView();
}
