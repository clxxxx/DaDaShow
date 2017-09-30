package com.xzry.takeshow.ui.order;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

@Module
public class OrderPresenterModule {

    private OrderContract.View view;

    public OrderPresenterModule(OrderContract.View view){ this.view = view; }

    @Provides
    OrderContract.View providerMainContractView(){
        return view;
    }

}
