package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.BrandShopEntity;

/**
 * @author: luosy
 * @date: 2017-9-16
 * @description:
 */


public interface BrandShopContract {
    interface View {
        void resultData(BrandShopEntity entity);
        void refreshDisplay();
    }

    interface Presenter {

    }
}
