package com.xzry.takeshow.ui.homepager.module;

import com.xzry.takeshow.ui.homepager.contract.SearchContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-7
 * @description:
 */

@Module
public class SearchModule {
    private SearchContract.View view;

    public SearchModule(SearchContract.View  view) {
        this.view = view;
    }

    @Provides
    SearchContract.View providerContractView(){
        return view;
    }
}
