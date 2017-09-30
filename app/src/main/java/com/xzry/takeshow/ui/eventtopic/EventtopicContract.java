package com.xzry.takeshow.ui.eventtopic;

import com.xzry.takeshow.entity.homeEntity.EventTopicComp;
import com.xzry.takeshow.entity.homeEntity.EventTopicEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-15
 * @description:
 */


public interface EventtopicContract {
    interface View{
        void getResult(List<EventTopicComp> list);
        void getResultChild(EventTopicEntity entity);
    }
    interface Presenter {

    }
}
