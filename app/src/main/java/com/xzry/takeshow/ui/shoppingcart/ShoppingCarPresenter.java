package com.xzry.takeshow.ui.shoppingcart;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.ShoppingCar;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

public class ShoppingCarPresenter extends BasePresenter implements ShoppingCarContract.Presenter {

    private DataManager mDataManager;

    private ShoppingCarContract.View mView;
    private static final String TAG = "OrderPresenter";

    @Inject
    public ShoppingCarPresenter(DataManager mDataManager, ShoppingCarContract.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;

    }

    @Override
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", BaseValue.TOKEN);
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_SHOPPINGCAR_URL,map, new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String result = null;
                try {
                    result = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult httpResult = new Gson().fromJson(result, HttpResult.class);
                if (TextUtils.equals(httpResult.getState() , "success")) {
                    Type objectType = new TypeToken<HttpResult<ShoppingCar>>() {}.getType();
                    HttpResult<ShoppingCar> httpResult2 = new Gson().fromJson(result, objectType);
                    mView.setData(httpResult2.getContent());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onComplete() {
            }
        });
        addDisposabe(disposable);
    }

    @Override
    public void deleteGoods(String skus) {
        Map<String, String> map = new HashMap<>();
        map.put("token", BaseValue.TOKEN);
        map.put("skus", skus);
        Disposable disposable = mDataManager.postRequestData(BaseValue.DELETE_SHOPPINGCAR_URL,map, new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String result = null;
                try {
                    result = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult httpResult = new Gson().fromJson(result, HttpResult.class);
                if (TextUtils.equals(httpResult.getState() , "success")) {
                    Type objectType = new TypeToken<HttpResult<ShoppingCar>>() {}.getType();
                    HttpResult<ShoppingCar> httpResult2 = new Gson().fromJson(result, objectType);
//                    mView.setData(httpResult2.getContent());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onComplete() {
            }
        });
        addDisposabe(disposable);
    }
}