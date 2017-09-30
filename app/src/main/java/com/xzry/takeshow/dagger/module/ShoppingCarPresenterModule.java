package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.shoppingcart.ShoppingCarContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

@Module
public class ShoppingCarPresenterModule {

    private ShoppingCarContract.View view;

    public ShoppingCarPresenterModule(ShoppingCarContract.View  view) {
        this.view = view;
    }

    @Provides
    ShoppingCarContract.View providerContractView(){
        return view;
    }
}
