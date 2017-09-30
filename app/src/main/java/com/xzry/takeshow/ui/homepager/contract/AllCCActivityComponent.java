package com.xzry.takeshow.ui.homepager.contract;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.ui.homepager.AllCommdityClassifyActivity;
import com.xzry.takeshow.ui.homepager.module.AllPresenterModule;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/9/6 0006.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = AllPresenterModule.class)
public interface AllCCActivityComponent {
    void inject(AllCommdityClassifyActivity activity);
}
