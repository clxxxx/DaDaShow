package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.BrandStreet;

import java.util.List;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

public interface BrandStreetContract {

    interface View {
        void setData(List<BrandStreet> data);
    }

    interface Presenter {

        void getData(int pageNo);

    }
}
