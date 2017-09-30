package com.xzry.takeshow.ui.integralshop.contract;

import com.xzry.takeshow.entity.homeEntity.IntegralCompEntity;
import com.xzry.takeshow.entity.homeEntity.IntegralEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-14
 * @description:
 */


public interface IntegralShopContract {
    interface View{
        void getResult(List<IntegralCompEntity> list);
        void getResultChild(IntegralEntity entity);
    }
    interface Presenter {

    }
}
