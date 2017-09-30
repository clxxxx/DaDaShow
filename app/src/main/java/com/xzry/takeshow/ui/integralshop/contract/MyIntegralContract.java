package com.xzry.takeshow.ui.integralshop.contract;

import com.xzry.takeshow.entity.IntegralInfo;
import com.xzry.takeshow.entity.MyIntegralEntity;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */


public interface MyIntegralContract {
    interface View{
        void resultInfoData(IntegralInfo info);
        void resultListData(MyIntegralEntity entity);
    }
    interface Presenter {

    }
}
