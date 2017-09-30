package com.xzry.takeshow.ui.order;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.OrderInfo;
import com.xzry.takeshow.entity.PageEntity;
import com.xzry.takeshow.entity.PaymentEntity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

public class ConfirmOrderPresenter extends BasePresenter implements ConfirmOrderContract.Presenter {

    private DataManager mDataManager;

    private ConfirmOrderContract.View mView;
    private static final String TAG = "OrderPresenter";

    @Inject
    public ConfirmOrderPresenter(DataManager mDataManager, ConfirmOrderContract.View mView) {
        this.mDataManager = mDataManager;
        this.mView = mView;

    }

    @Override
    public void submitOrder(Map<String, String> map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.SUBMIT_ORDER_URL, map, new ErrorDisposableObserver<ResponseBody>() {
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
                    Type objectType = new TypeToken<HttpResult<PaymentEntity>>() {}.getType();
                    HttpResult<PaymentEntity> httpResult2 = new Gson().fromJson(result, objectType);
                    mView.payment(httpResult2.getContent());
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
