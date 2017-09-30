package com.xzry.takeshow.ui.homepager.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.BrandHomeEntity;
import com.xzry.takeshow.entity.BrandStreet;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.PageEntity;
import com.xzry.takeshow.ui.homepager.contract.BrandContract;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/9/8 0008.
 */

public class BrandPresenter extends BasePresenter implements BrandContract.Presenter {

    private DataManager mDataManager;

    private BrandContract.View mView;

    @Inject
    public BrandPresenter(DataManager mDataManager, BrandContract.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;

    }


    @Override
    public void getData(String brandID) {
        Disposable disposable = mDataManager.getRequestData(BaseValue.BRAND_URL + brandID, new ErrorDisposableObserver<ResponseBody>() {
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
                    Type objectType = new TypeToken<HttpResult<BrandHomeEntity>>() {}.getType();
                    HttpResult<BrandHomeEntity> httpResult2 = new Gson().fromJson(result, objectType);
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
}
