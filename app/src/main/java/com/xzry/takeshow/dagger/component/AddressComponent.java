package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.AddressModule;
import com.xzry.takeshow.ui.mine.ADDaddressActivity;
import com.xzry.takeshow.ui.mine.AddressActivity;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = AddressModule.class)
public interface AddressComponent {
    void inject(AddressActivity activity);
    void inject(ADDaddressActivity activity);
}
