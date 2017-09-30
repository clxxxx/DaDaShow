package com.xzry.takeshow.ui.homepager.module;

import com.xzry.takeshow.ui.homepager.contract.AllCCContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/9/6 0006.
 */

@Module
public class AllPresenterModule {
    private AllCCContract.View view;

    public AllPresenterModule(AllCCContract.View  view) {
        this.view = view;
    }

    @Provides
    AllCCContract.View providerMainContractView(){
        return view;
    }
}
