package com.xzry.takeshow.ui.integralshop.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.IntegralInfo;
import com.xzry.takeshow.entity.MyIntegralEntity;
import com.xzry.takeshow.ui.integralshop.contract.MyIntegralContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */


public class MyIntegralPresenter extends BasePresenter implements MyIntegralContract.Presenter{
    private DataManager mDataManager;
    private MyIntegralContract.View view;
    @Inject
    public MyIntegralPresenter(DataManager dataManager, MyIntegralContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }
    public void getIntegralInfoData(String token){
        Map map = new HashMap();
        map.put("token",token);
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MY_INTEGRAL_URL,map,new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult httpResult = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(httpResult.getState(),"success")) {
                    if (TextUtils.equals(httpResult.getCode(),"13000")) {
                        HttpResult<IntegralInfo> result = new Gson().fromJson(string,new TypeToken<HttpResult<IntegralInfo>>(){}.getType());
                        view.resultInfoData(result.getContent());
                    }
                }else {

                }

            }

            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }
    public void getIntegralListData(String token,int page) {
        Map map = new HashMap();
        map.put("token",token);
        map.put("pageNo",page);
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MY_INTEGRAL_LIST_URL,map,new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult httpResult = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(httpResult.getState(),"success")) {
                    if (TextUtils.equals(httpResult.getCode(),"13000")) {
                        HttpResult<MyIntegralEntity> result = new Gson().fromJson(string,new TypeToken<HttpResult<MyIntegralEntity>>(){}.getType());
                        view.resultListData(result.getContent());
                    }
                }else {

                }

            }

            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }
}
