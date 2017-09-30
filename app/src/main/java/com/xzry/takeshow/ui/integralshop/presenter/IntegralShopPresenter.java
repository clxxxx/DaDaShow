package com.xzry.takeshow.ui.integralshop.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.HttpResults;
import com.xzry.takeshow.entity.homeEntity.IntegralCompEntity;
import com.xzry.takeshow.entity.homeEntity.IntegralEntity;
import com.xzry.takeshow.ui.integralshop.contract.IntegralShopContract;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-14
 * @description:
 */


public class IntegralShopPresenter extends BasePresenter implements IntegralShopContract.Presenter {
    private DataManager mDataManager;
    private IntegralShopContract.View view;
    @Inject
    public IntegralShopPresenter(DataManager dataManager, IntegralShopContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }

    public void getAllData() {
        Disposable disposable = mDataManager.getRequestData(BaseValue.INTEGRAL_SHOP_URL,new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResults httpResult = new Gson().fromJson(string,HttpResults.class);
                if (TextUtils.equals(httpResult.getState(),"success")) {
                    if (TextUtils.equals(httpResult.getCode(),"13000")) {
                        HttpResults<IntegralCompEntity> result = new Gson().fromJson(string,new TypeToken<HttpResults<IntegralCompEntity>>(){}.getType());
                        view.getResult(result.getContent());
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
    public void getAllInfo(int int1,int int2){
        Disposable disposable = mDataManager.getRequestData(BaseValue.INTEGRAL_SHOP_INFO_URL+"/"+int1+"/"+int2,new ErrorDisposableObserver<ResponseBody>() {
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
                        HttpResult<IntegralEntity> result = new Gson().fromJson(string,new TypeToken<HttpResult<IntegralEntity>>(){}.getType());
                        view.getResultChild(result.getContent());
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
