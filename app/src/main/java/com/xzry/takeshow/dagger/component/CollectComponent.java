package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerFragment;
import com.xzry.takeshow.dagger.module.CollectModule;
import com.xzry.takeshow.ui.mine.CollectGoodsFragment;
import com.xzry.takeshow.ui.mine.CollectMerchantFragment;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-21
 * @description:
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = CollectModule.class)
public interface CollectComponent {
    void inject(CollectGoodsFragment fragment);
    void inject(CollectMerchantFragment fragment);
}
