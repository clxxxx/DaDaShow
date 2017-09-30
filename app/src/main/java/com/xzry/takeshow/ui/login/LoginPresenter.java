package com.xzry.takeshow.ui.login;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.UserInfo;
import com.xzry.takeshow.utils.SpUtil;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

import static com.xzry.takeshow.base.BaseValue.User.HEADIMG;
import static com.xzry.takeshow.base.BaseValue.User.NICKNAME;

/**
 * @author: luosy
 * @date: 2017-8-30
 * @description:
 */


public class LoginPresenter extends BasePresenter implements LoginContract.Presenter{
    String TAG = "login";
    private DataManager dataManager;
    private LoginContract.View view;
    @Inject
    public LoginPresenter(DataManager dataManager,LoginContract.View view){
        this.dataManager = dataManager;
        this.view = view;
    }

    @Override
    public void getCode(String mobile) {

        Disposable disposable = dataManager.getRequestData(BaseValue.GETCODE_URL+"/"+mobile,new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    Log.i(TAG,"----------"+string);
                    HttpResult httpResult = new Gson().fromJson(string, HttpResult.class);
                    if (httpResult.getState().equals("success")) {
                        Log.i(TAG,"----------"+1);
                        view.showToast(1);
                    }else  {
                        Log.i(TAG,"----------"+0);
                        view.showToast(0);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }

    public void toLogin(Object mp) {
        Disposable disposable = dataManager.postRequestData(BaseValue.LOGIN_URL, mp, new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {

                try {
                    String string = responseBody.string();
                    Log.i("login","---------------"+string);
                    HttpResult httpResult = new Gson().fromJson(string, HttpResult.class);


                    if (httpResult.getState().equals("success")) {
                        if (TextUtils.equals(httpResult.getCode(),"13000")) {
                            HttpResult<UserInfo> respon = new Gson().fromJson(string, new TypeToken<HttpResult<UserInfo>>() {}.getType());
                            UserInfo user = respon.getContent();
                            view.showLoginResult(user);
                            keepUserData(user);
                        }else {

                        }


                    } else {
                        view.showLoginError();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }
    /**
     * 保存用户数据
     */
    public static void keepUserData(UserInfo userInfo) {
        Log.i("login","登录成功------id:"+userInfo.id+"--token:"+userInfo.code);

//        SpUtil.getInstance().putBoolean(ISLOGIN, true);                           //登录状态
//        SpUtil.getInstance().putString(USERID, userInfo.id);                      //id
          SpUtil.getInstance().putString(BaseValue.User.TOKEN, userInfo.code);                      //Md5值，用户生成唯一标识
//
//        SpUtil.getInstance().putString(MOBILE, userInfo.mobile);                  //手机号
          SpUtil.getInstance().putString(NICKNAME, userInfo.nickname);                 //昵称
//        SpUtil.getInstance().putInt(SEX, userInfo.sex);                        //性别
//        SpUtil.getInstance().putString(PERSONALITY, userInfo.personality);        //个性描述
          SpUtil.getInstance().putString(HEADIMG, userInfo.headerUrl);              //头像
//        SpUtil.getInstance().putString(MARTURL, userInfo.martUrl);                 //二维码
//
//        SpUtil.getInstance().putString(VISITCOUNT, userInfo.visitCount);          //登录次数
//        SpUtil.getInstance().putString(LASTLOGIN, userInfo.lastLogin);            //最后一次登录时间


    }
}
