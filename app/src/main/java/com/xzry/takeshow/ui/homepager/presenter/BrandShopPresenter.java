package com.xzry.takeshow.ui.homepager.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.BrandShopEntity;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.ui.homepager.contract.BrandShopContract;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-16
 * @description:
 */


public class BrandShopPresenter extends BasePresenter implements BrandShopContract.Presenter{

    private DataManager mDataManager;

    private BrandShopContract.View mView;

    @Inject
    public BrandShopPresenter(DataManager mDataManager, BrandShopContract.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;

    }

    public void getData(Map mp) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.BRAND_SHOP_MAIN_URL,mp, new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult httpResult = new Gson().fromJson(string, HttpResult.class);
                if (TextUtils.equals(httpResult.getState() , "success")) {
                    if(TextUtils.equals(httpResult.getCode() , "13000")){
                        HttpResult<BrandShopEntity> result = new Gson().fromJson(string, new TypeToken<HttpResult<BrandShopEntity>>(){}.getType());
                        BrandShopEntity entity = new BrandShopEntity(BrandShopEntity.TYPE_NEW_PRODUCT);
                        entity = result.getContent();
                        mView.resultData(entity);
                    }
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

