package com.xzry.takeshow.ui.homepager.module;

import com.xzry.takeshow.ui.homepager.contract.NearbyStoresContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */

@Module
public class NearbyStoresModule {
    private NearbyStoresContract.View view;
    public NearbyStoresModule(NearbyStoresContract.View view){
        this.view = view;
    }
    @Provides
    NearbyStoresContract.View providerNearbyStoresContractView(){
        return view;
    }
}
