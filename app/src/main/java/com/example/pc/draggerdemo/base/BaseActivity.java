package com.example.pc.draggerdemo.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.pc.draggerdemo.application.NewsApplication;
import com.example.pc.draggerdemo.di.component.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by PC on 12/31/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    protected abstract int getContentView();


    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDependencies();
    }

    protected void resolveDependencies() {

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
}