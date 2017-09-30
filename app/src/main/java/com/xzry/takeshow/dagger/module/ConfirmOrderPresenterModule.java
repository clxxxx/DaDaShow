package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.order.ConfirmOrderContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

@Module
public class ConfirmOrderPresenterModule {

    private ConfirmOrderContract.View view;

    public ConfirmOrderPresenterModule(ConfirmOrderContract.View  view) {
        this.view = view;
    }

    @Provides
    ConfirmOrderContract.View providerContractView(){
        return view;
    }
}
