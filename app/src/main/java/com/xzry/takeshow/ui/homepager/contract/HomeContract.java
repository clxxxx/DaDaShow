package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.homeEntity.HomeEntity;

/**
 * Created by 周东阳 on 2017/8/5 0005.
 */

public interface HomeContract {
    interface View {
        void setHomeData(int position, HomeEntity data);
    }

    interface Presenter {
        void getHomeData(int page);
    }

}