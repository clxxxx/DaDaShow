package com.xzry.takeshow.ui.homepager.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.CategoryEntity;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.ui.homepager.contract.AllCCContract;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/9/6 0006.
 */

public class AllCommodityPresenter extends BasePresenter implements AllCCContract.Presenter {

    private DataManager mDataManager;
    private AllCCContract.View mView;

    @Inject
    public AllCommodityPresenter(DataManager mDataManager, AllCCContract.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;

    }

    @Override
    public void getData() {
        Disposable disposable = mDataManager.getRequestData(BaseValue.ALL_COMMODITY_CLASSIFY_URL, new ErrorDisposableObserver<ResponseBody>() {
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
                    Type objectType = new TypeToken<HttpResult<List<CategoryEntity>>>() {}.getType();
                    HttpResult<List<CategoryEntity>> httpResult2 = new Gson().fromJson(result, objectType);
                    mView.setFirstData(httpResult2.getContent());
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
    public void getSecondData(String id) {
        Disposable disposable = mDataManager.getRequestData(BaseValue.ALL_COMMODITY_CLASSIFY_URL2 + id, new ErrorDisposableObserver<ResponseBody>() {
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
                    Type objectType = new TypeToken<HttpResult<List<CategoryEntity>>>() {}.getType();
                    HttpResult<List<CategoryEntity>> httpResult2 = new Gson().fromJson(result, objectType);
                    mView.setSecondData(httpResult2.getContent());
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
