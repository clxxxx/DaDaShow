package com.xzry.takeshow.dagger.module;

import com.xzry.takeshow.ui.eventtopic.EventtopicContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: luosy
 * @date: 2017-9-15
 * @description:
 */

@Module
public class EventTopicModule {
    private EventtopicContract.View view;

    public EventTopicModule(EventtopicContract.View  view) {
        this.view = view;
    }

    @Provides
    EventtopicContract.View providerContractView(){
        return view;
    }
}
