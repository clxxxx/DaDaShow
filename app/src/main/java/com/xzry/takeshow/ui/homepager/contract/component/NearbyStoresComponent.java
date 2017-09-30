package com.xzry.takeshow.ui.homepager.contract.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.ui.homepager.NearbyStoresListActivity;
import com.xzry.takeshow.ui.homepager.module.NearbyStoresModule;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = NearbyStoresModule.class)
public interface NearbyStoresComponent {
    void inject(NearbyStoresListActivity activity);
}
