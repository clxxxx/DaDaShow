package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.homepager.contract.BrandStreetContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

@Module
public class BSPresenterModule {

    private BrandStreetContract.View view;

    public BSPresenterModule(BrandStreetContract.View  view) {
        this.view = view;
    }

    @Provides
    BrandStreetContract.View providerContractView(){
        return view;
    }
}
