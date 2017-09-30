package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.integralshop.contract.MyIntegralContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */

@Module
public class MyIntegralModule {
    private MyIntegralContract.View view;

    public MyIntegralModule(MyIntegralContract.View  view) {
        this.view = view;
    }

    @Provides
    MyIntegralContract.View providerContractView(){
        return view;
    }
}
