package com.xzry.takeshow.ui.order;

import com.xzry.takeshow.entity.PaymentEntity;

import java.util.Map;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

public interface ConfirmOrderContract {

    interface View {
        void payment(PaymentEntity paymentEntity);
    }

    interface Presenter {
        void submitOrder(Map<String, String> map);
    }
}
