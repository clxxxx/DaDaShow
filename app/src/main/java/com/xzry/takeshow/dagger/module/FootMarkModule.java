package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.mine.contract.FootMarkContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */

@Module
public class FootMarkModule {
    private FootMarkContract.View view;

    public FootMarkModule(FootMarkContract.View  view) {
        this.view = view;
    }

    @Provides
    FootMarkContract.View providerContractView(){
        return view;
    }
}

