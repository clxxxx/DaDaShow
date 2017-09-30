package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.BrandShopModule;
import com.xzry.takeshow.ui.homepager.BrandShopActivity;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-16
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = BrandShopModule.class)
public interface BrandShopComponent {
    void inject(BrandShopActivity activity);
}
