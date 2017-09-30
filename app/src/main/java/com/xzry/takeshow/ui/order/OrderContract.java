package com.xzry.takeshow.ui.order;

import com.xzry.takeshow.entity.OrderInfo;
import com.xzry.takeshow.entity.PageEntity;

import java.util.Map;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

public interface OrderContract {

    interface View {
        void setView(PageEntity<OrderInfo> data);
    }

    interface Presenter {
        void getData(Map<String, String> map);
    }

}
