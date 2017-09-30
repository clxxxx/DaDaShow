package com.xzry.takeshow.ui.homepager.module;

import com.xzry.takeshow.ui.homepager.contract.SearchDataContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */

@Module
public class SearchDataModule {
    private SearchDataContract.View view;

    public SearchDataModule(SearchDataContract.View  view) {
        this.view = view;
    }

    @Provides
    SearchDataContract.View providerSearchDataContractView(){
        return view;
    }
}
