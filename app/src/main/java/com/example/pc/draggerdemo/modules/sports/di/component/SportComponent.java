package com.example.pc.draggerdemo.modules.sports.di.component;

import com.example.pc.draggerdemo.base.di.component.ApplicationComponent;
import com.example.pc.draggerdemo.base.di.scope.PerActivity;
import com.example.pc.draggerdemo.modules.news.NewsFragment;
import com.example.pc.draggerdemo.modules.sports.SportsFragment;
import com.example.pc.draggerdemo.modules.sports.di.module.SportModule;

import dagger.Component;

/**
 * Created by PC on 4/28/2018.
 */


@PerActivity
@Component(modules = SportModule.class, dependencies = ApplicationComponent.class)
public interface SportComponent {
    void inject(SportsFragment newsFragment);
}
