package com.xzry.takeshow.ui.commodityInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/9/2 0002.
 */

@Module
public class CommodityPresenterModule {
    private CommodityContract.View view;

    public CommodityPresenterModule(CommodityContract.View  view) {
        this.view = view;
    }

    @Provides
    CommodityContract.View providerMainContractView(){
        return view;
    }

}
