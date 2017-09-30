package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.homepager.contract.BrandShopContract;
import com.xzry.takeshow.ui.mine.contract.AddressContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-16
 * @description:
 */

@Module
public class BrandShopModule {
    private BrandShopContract.View view;
    public BrandShopModule(BrandShopContract.View view){
        this.view = view;
    }
    @Provides
    BrandShopContract.View providerContractView(){
        return view;
    }
}
