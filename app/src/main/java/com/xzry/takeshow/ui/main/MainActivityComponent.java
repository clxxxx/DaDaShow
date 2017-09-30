package com.xzry.takeshow.ui.main;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/8/3 0003.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = MainPresenterModule .class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}

