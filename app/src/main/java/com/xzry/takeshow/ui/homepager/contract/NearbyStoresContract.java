package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.NearbyStoresEntity;

import java.util.Map;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */


public interface NearbyStoresContract {
    interface View{
        void resultData(NearbyStoresEntity entity);
    }
    interface Presenter {
        void toGetData(Map mp);
    }
}
