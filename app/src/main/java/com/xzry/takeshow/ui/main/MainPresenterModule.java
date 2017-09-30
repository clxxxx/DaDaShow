package com.xzry.takeshow.ui.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 周东阳 on 2017/8/3 0003.
 */

@Module
public class MainPresenterModule {
    private MainContract.View view;

    public MainPresenterModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    MainContract.View providerMainContractView(){
        return view;
    }
}
