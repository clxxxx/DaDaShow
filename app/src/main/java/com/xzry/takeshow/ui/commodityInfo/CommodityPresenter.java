package com.xzry.takeshow.ui.commodityInfo;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.commodity.Goods;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/9/2 0002.
 */

public class CommodityPresenter extends BasePresenter implements CommodityContract.Presenter {

    private DataManager mDataManager;

    private CommodityContract.View mView;
    private static final String TAG = "CommodityPresenter";

    @Inject
    public CommodityPresenter(DataManager mDataManager, CommodityContract.View view) {
        this.mDataManager = mDataManager;
        this.mView = view;

    }

    @Override
    public void getData(Map<String, String> map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.COMMODITY_INFO_URL,map, new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String result = null;
                try {
                    result = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("COMMODITY_INFO_URL", result);
                HttpResult httpResult = new Gson().fromJson(result, HttpResult.class);
                if (TextUtils.equals(httpResult.getState() , "success")) {
                    Type objectType = new TypeToken<HttpResult<Goods>>() {}.getType();
                    HttpResult<Goods> httpResult2 = new Gson().fromJson(result, objectType);
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
    public void checkStock(Map<String, String> map, DisposableObserver consumer) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.COMMODITY_INVENTORY_URL,map, consumer);
        addDisposabe(disposable);
    }

    @Override
    public void addShoppingcar(Map<String, String> map, DisposableObserver consumer) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.ADD_SHOPPINGCAR_URL,map, consumer);
        addDisposabe(disposable);
    }

    @Override
    public void collect(Map<String, String> map, DisposableObserver consumer) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.COLLECT_URL,map, consumer);
        addDisposabe(disposable);
    }

}
