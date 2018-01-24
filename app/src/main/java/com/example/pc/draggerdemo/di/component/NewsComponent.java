package com.example.pc.draggerdemo.di.component;

import com.example.pc.draggerdemo.modules.MainActivity;
import com.example.pc.draggerdemo.di.module.NewsModule;
import com.example.pc.draggerdemo.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by PC on 12/31/2017.
 */

@PerActivity
@Component(modules = NewsModule.class, dependencies = ApplicationComponent.class)
public interface NewsComponent {
    void inject(MainActivity activity);
}
