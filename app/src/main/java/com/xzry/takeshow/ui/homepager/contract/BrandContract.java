package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.BrandHomeEntity;

/**
 * Created by 周东阳 on 2017/9/8 0008.
 */

public interface BrandContract {

    interface View {
        void setData(BrandHomeEntity data);
    }

    interface Presenter {

        void getData(String brandID);

    }
}
