package com.xzry.takeshow.ui.login;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerFragment;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-8-30
 * @description:
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
    void inject(BindMobileActivity activity);
}
