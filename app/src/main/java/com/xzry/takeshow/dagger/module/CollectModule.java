package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.mine.contract.CollectContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-21
 * @description:
 */

@Module
public class CollectModule {
    private CollectContract.View view;

    public CollectModule(CollectContract.View  view) {
        this.view = view;
    }

    @Provides
    CollectContract.View providerContractView(){
        return view;
    }
}

