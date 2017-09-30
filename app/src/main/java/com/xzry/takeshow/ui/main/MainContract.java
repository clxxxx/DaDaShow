package com.xzry.takeshow.ui.main;

import com.xzry.takeshow.entity.UserInfo;

/**
 * Created by 周东阳 on 2017/8/3 0003.
 */

public interface MainContract {
    interface View {
        void loginResult(UserInfo user);
        void loginErrorResult();
    }

    interface Presenter {

    }

}
