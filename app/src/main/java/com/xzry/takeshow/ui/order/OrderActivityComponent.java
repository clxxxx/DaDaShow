package com.xzry.takeshow.ui.order;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = OrderPresenterModule.class)
public interface OrderActivityComponent {
    void inject(OrderActivity activity);
}
