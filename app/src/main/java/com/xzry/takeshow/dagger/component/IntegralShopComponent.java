package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.IntegralShopModule;
import com.xzry.takeshow.ui.integralshop.IntegralShopActivity;
import com.xzry.takeshow.ui.integralshop.IntegralTabOneFragment;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-14
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = IntegralShopModule.class)
public interface IntegralShopComponent {
    void inject(IntegralShopActivity activity);
    void inject(IntegralTabOneFragment fragment);
}
