package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerFragment;
import com.xzry.takeshow.dagger.module.MineFragmentModule;
import com.xzry.takeshow.ui.mine.MineFragment;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-19
 * @description:
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = MineFragmentModule.class)
public interface MineFragmentComponent {
    void inject(MineFragment fragment);

}

