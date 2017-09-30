package com.xzry.takeshow.ui.mine.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.umeng.socialize.utils.Log;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.CollectGoods;
import com.xzry.takeshow.entity.CollectMerchant;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.ui.mine.contract.CollectContract;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-21
 * @description:
 */


public class CollectPresenter extends BasePresenter implements CollectContract.Presenter{
    String TAG = "coll";
    private DataManager mDataManager;
    private CollectContract.View view;
    @Inject
    public CollectPresenter(DataManager dataManager, CollectContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }
    public void getGoodsList(Map map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MY_GOODS_LIST_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"-----我的关注----------"+string);
                HttpResult http = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(http.getState(),"success")) {
                    if (TextUtils.equals(http.getCode(),"13000")) {
                        HttpResult<CollectGoods> result = new Gson().fromJson(string,new TypeToken<HttpResult<CollectGoods>>(){}.getType());
                        view.resultGoodsSuccess(result.getContent());
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
    public void getMerchantList(Map map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MY_MERCHANT_LIST_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"-----我的关注----------"+string);
                HttpResult http = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(http.getState(),"success")) {
                    if (TextUtils.equals(http.getCode(),"13000")) {
                        HttpResult<CollectMerchant> result = new Gson().fromJson(string,new TypeToken<HttpResult<CollectMerchant>>(){}.getType());
                        view.resultMerchantSuccess(result.getContent());
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
