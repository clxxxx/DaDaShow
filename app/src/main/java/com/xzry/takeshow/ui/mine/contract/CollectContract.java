package com.xzry.takeshow.ui.mine.contract;

import com.xzry.takeshow.entity.CollectGoods;
import com.xzry.takeshow.entity.CollectMerchant;

/**
 * @author: luosy
 * @date: 2017-9-21
 * @description:
 */


public interface CollectContract {
    interface View {

        void resultGoodsSuccess(CollectGoods goods);
        void resultMerchantSuccess(CollectMerchant merchant);
    }

    interface Presenter {

    }
}
