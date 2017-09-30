package com.xzry.takeshow.ui.shoppingcart;

import com.xzry.takeshow.entity.ShoppingCar;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

public interface ShoppingCarContract {

    interface View {
        void setData(ShoppingCar data);
        void deletedResult();
    }

    interface Presenter {
        void getData();
        void deleteGoods(String skus);
    }

}
