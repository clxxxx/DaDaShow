package com.xzry.takeshow.ui.login;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-8-30
 * @description:
 */

@Module
public class LoginModule {
    private LoginContract.View view;

    public LoginModule(LoginContract.View  view) {
        this.view = view;
    }

    @Provides
    LoginContract.View providerLoginContractView(){
        return view;
    }
}
