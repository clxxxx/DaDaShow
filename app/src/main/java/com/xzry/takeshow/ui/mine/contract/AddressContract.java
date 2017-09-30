package com.xzry.takeshow.ui.mine.contract;

import com.xzry.takeshow.entity.AddressEntity;

import java.util.List;
import java.util.Map;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */


public interface AddressContract {
    interface View {
        void resultData(List<AddressEntity> list);
        void refreshDisplay(String str);
    }

    interface Presenter {
       void toGetAddress(Map mp);
       void toAddAddress(Map mp);
    }
}
