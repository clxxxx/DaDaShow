package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerFragment;
import com.xzry.takeshow.dagger.module.ShoppingCarPresenterModule;
import com.xzry.takeshow.ui.shoppingcart.ShoppingCartFragment;

import dagger.Component;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = ShoppingCarPresenterModule.class)
public interface ShoppingCarFragmentComponent {
    void inject(ShoppingCartFragment fragment);
}

