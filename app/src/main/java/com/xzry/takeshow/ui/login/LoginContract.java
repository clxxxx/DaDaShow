package com.xzry.takeshow.ui.login;

import com.xzry.takeshow.entity.UserInfo;

/**
 * @author: luosy
 * @date: 2017-8-30
 * @description:
 */


public class LoginContract {
    interface View {
        void showLoginResult(UserInfo user);
        void showLoginError();
        void showToast(int state);

    }

    interface Presenter {
        void getCode(String mobile);
        void toLogin(Object mp);
    }
}
