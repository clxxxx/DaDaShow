package com.xzry.takeshow.dagger.module;


import com.xzry.takeshow.ui.homepager.contract.BrandContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/9/8 0008.
 */

@Module
public class BrandPresenterModule {
    private BrandContract.View view;

    public BrandPresenterModule(BrandContract.View  view) {
        this.view = view;
    }

    @Provides
    BrandContract.View providerContractView(){
        return view;
    }
}
