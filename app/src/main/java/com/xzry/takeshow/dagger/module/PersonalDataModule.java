package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.mine.contract.PersonalDataContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-19
 * @description:
 */


@Module
public class PersonalDataModule {
    private PersonalDataContract.View view;

    public PersonalDataModule(PersonalDataContract.View  view) {
        this.view = view;
    }

    @Provides
    PersonalDataContract.View providerContractView(){
        return view;
    }
}

