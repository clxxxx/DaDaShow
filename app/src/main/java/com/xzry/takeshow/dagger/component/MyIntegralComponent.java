package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.MyIntegralModule;
import com.xzry.takeshow.ui.integralshop.MyIntegralActivity;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = MyIntegralModule.class)
public interface MyIntegralComponent {
    void inject(MyIntegralActivity activity);

}

