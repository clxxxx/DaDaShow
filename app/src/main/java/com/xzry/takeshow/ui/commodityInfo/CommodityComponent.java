package com.xzry.takeshow.ui.commodityInfo;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/9/2 0002.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = CommodityPresenterModule.class)
public interface CommodityComponent {
    void inject(CommodityInfoActivity activity);
}
