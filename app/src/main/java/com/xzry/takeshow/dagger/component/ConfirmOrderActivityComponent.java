package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.ConfirmOrderPresenterModule;
import com.xzry.takeshow.ui.order.ConfirmOrderActivity;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = ConfirmOrderPresenterModule.class)
public interface ConfirmOrderActivityComponent {
    void inject(ConfirmOrderActivity activity);
}
