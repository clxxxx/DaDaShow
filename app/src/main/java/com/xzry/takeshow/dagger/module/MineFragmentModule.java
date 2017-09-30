package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.mine.contract.MineFragmentContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-19
 * @description:
 */

@Module
public class MineFragmentModule {
    private MineFragmentContract.View view;

    public MineFragmentModule(MineFragmentContract.View  view) {
        this.view = view;
    }

    @Provides
    MineFragmentContract.View providerContractView(){
        return view;
    }
}
