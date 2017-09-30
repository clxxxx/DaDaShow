package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.AttentionModule;
import com.xzry.takeshow.ui.mine.AttentionActivity;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */


@PerActivity
@Component(dependencies = AppComponent.class , modules = AttentionModule.class)
public interface AttentionComponent {
    void inject(AttentionActivity activity);

}
