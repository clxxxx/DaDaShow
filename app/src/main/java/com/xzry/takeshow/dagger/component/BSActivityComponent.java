package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.BSPresenterModule;
import com.xzry.takeshow.ui.homepager.BrandStreetActivity;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = BSPresenterModule.class)
public interface BSActivityComponent {
    void inject(BrandStreetActivity activity);
}
