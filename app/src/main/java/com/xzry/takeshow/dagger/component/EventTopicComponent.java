package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.EventTopicModule;
import com.xzry.takeshow.ui.eventtopic.EventTabOneFragment;
import com.xzry.takeshow.ui.eventtopic.EventTopicActivity;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-15
 * @description:
 */


@PerActivity
@Component(dependencies = AppComponent.class , modules = EventTopicModule.class)
public interface EventTopicComponent {
    void inject(EventTopicActivity activity);
    void inject(EventTabOneFragment fragment);
}
