package com.xzry.takeshow.ui.homepager.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.SubscriberOnNextListener;
import com.sxjs.common.model.DataManager;
import com.sxjs.common.model.http.HttpResult;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.base.NextDisposableObserver;
import com.xzry.takeshow.entity.BrandStreet;
import com.xzry.takeshow.entity.PageEntity;
import com.xzry.takeshow.ui.homepager.contract.BrandStreetContract;

import java.lang.reflect.Type;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

public class BrandStreetPresenter extends BasePresenter implements BrandStreetContract.Presenter{

    private DataManager mDataManager;

    private BrandStreetContract.View mView;

    @Inject
    public BrandStreetPresenter(DataManager mDataManager, BrandStreetContract.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;

    }

    @Override
    public void getData(int pageNo) {
        Disposable disposable = mDataManager.getRequestData(BaseValue.BRAND_STREET_URL + pageNo, new NextDisposableObserver(
                new SubscriberOnNextListener(){

                    @Override
                    public void onNext(String result) {
                        Type objectType = new TypeToken<HttpResult<PageEntity<BrandStreet>>>() {}.getType();
                        HttpResult<PageEntity<BrandStreet>> httpResult = new Gson().fromJson(result, objectType);
                        mView.setData(httpResult.getContent().rows);
                    }

                    @Override
                    public void onError(int code) {

                    }
                }
        ));
        addDisposabe(disposable);
    }
}
