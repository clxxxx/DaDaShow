package com.xzry.takeshow.ui.main;

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

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/8/3 0003.
 */

public class MainPresenter extends BasePresenter implements MainContract.Presenter{
    private DataManager dataManager;

    private MainContract.View view;
    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager mDataManager, MainContract.View view) {
        this.dataManager = mDataManager;
        this.view = view;

    }
    public void toTokenLogin(Object mp) {
        Disposable disposable = dataManager.postRequestData(BaseValue.LOGIN_URL, mp, new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {

                try {
                    String string = responseBody.string();
                    Log.i("login","---------------"+string);
                    HttpResult http = new Gson().fromJson(string,HttpResult.class);
                    if (TextUtils.equals(http.getState(),"success")) {
                        if (TextUtils.equals(http.getCode(),"13000")) {
                            HttpResult<UserInfo> result= new Gson().fromJson(string, new TypeToken<HttpResult<UserInfo>>() {}.getType());
                            view.loginResult(result.getContent());
                            //keepUserData(user);
                        }
                    }else {
                        view.loginErrorResult();
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

}
