package com.xzry.takeshow.ui.homepager.contract;


import com.sxjs.common.AppComponent;
import com.sxjs.common.PerFragment;
import com.xzry.takeshow.ui.homepager.HomeFragment;
import com.xzry.takeshow.ui.homepager.module.HomePresenterModule;

import dagger.Component;
/**
 * Created by 周东阳 on 2017/8/5 0005.
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = HomePresenterModule.class)
public interface HomeFragmentComponent {
    void inject(HomeFragment fragment);
}
