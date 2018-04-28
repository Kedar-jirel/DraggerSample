package com.example.pc.draggerdemo.modules.main;

import com.example.pc.draggerdemo.di.component.ApplicationComponent;

/**
 * Created by PC on 4/2/2018.
 */

public interface FragmentChangeListener {
    void onShowProgressDalog(String s);

    void onHideProgressDialog();

    ApplicationComponent getMainComponent();
}
