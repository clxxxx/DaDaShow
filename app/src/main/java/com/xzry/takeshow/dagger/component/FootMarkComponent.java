package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.FootMarkModule;
import com.xzry.takeshow.ui.mine.FootMarkActivity;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = FootMarkModule.class)
public interface FootMarkComponent {
    void inject(FootMarkActivity activity);
}

