package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.integralshop.contract.IntegralShopContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-14
 * @description:
 */

@Module
public class IntegralShopModule {
    private IntegralShopContract.View view;

    public IntegralShopModule(IntegralShopContract.View  view) {
        this.view = view;
    }

    @Provides
    IntegralShopContract.View providerContractView(){
        return view;
    }
}
