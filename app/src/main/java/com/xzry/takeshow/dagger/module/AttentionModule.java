package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.mine.contract.AttentionContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */


@Module
public class AttentionModule {
    private AttentionContract.View view;
    public AttentionModule(AttentionContract.View view){
        this.view = view;
    }
    @Provides
    AttentionContract.View providerContractView(){
        return view;
    }
}

