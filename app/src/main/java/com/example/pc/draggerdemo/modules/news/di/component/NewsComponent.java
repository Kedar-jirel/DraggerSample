package com.example.pc.draggerdemo.modules.news.di.component;

import com.example.pc.draggerdemo.base.di.component.ApplicationComponent;
import com.example.pc.draggerdemo.modules.news.di.module.NewsModule;
import com.example.pc.draggerdemo.base.di.scope.PerActivity;
import com.example.pc.draggerdemo.modules.news.NewsFragment;

import dagger.Component;

/**
 * Created by PC on 12/31/2017.
 */

@PerActivity
@Component(modules = NewsModule.class, dependencies = ApplicationComponent.class)
public interface NewsComponent {
    void inject(NewsFragment newsFragment);
}
