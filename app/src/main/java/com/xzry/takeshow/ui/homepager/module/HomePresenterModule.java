package com.xzry.takeshow.ui.homepager.module;

import com.xzry.takeshow.ui.homepager.contract.HomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/8/5 0005.
 */

@Module
public class HomePresenterModule {
    private HomeContract.View view;

    public HomePresenterModule(HomeContract.View  view) {
        this.view = view;
    }

    @Provides
    HomeContract.View providerMainContractView(){
        return view;
    }
}
