package com.xzry.takeshow.ui.mine.contract;

import com.xzry.takeshow.entity.UserData;

/**
 * @author: luosy
 * @date: 2017-9-19
 * @description:
 */


public interface PersonalDataContract {
    interface View {
        void resultData(UserData data);
        void resultHeadImgData(String str);
        void resultNickNameData(String name);
    }

    interface Presenter {

    }
}
