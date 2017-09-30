package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.mine.contract.AddressContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */

@Module
public class AddressModule {
    private AddressContract.View view;
    public AddressModule(AddressContract.View view){
        this.view = view;
    }
    @Provides
    AddressContract.View providerContractView(){
        return view;
    }
}
