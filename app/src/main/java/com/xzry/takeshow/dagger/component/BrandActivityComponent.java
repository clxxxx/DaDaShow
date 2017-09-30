package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.BrandPresenterModule;
import com.xzry.takeshow.ui.homepager.BrandActivity;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/9/8 0008.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = BrandPresenterModule.class)
public interface BrandActivityComponent {
    void inject(BrandActivity activity);
}
