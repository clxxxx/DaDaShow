package com.xzry.takeshow.ui.mine.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.umeng.socialize.utils.Log;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.AttentionEntity;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.ui.mine.contract.AttentionContract;

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


public class AttentionPresenter extends BasePresenter implements AttentionContract.Presenter{
    String TAG = "att";
    private DataManager mDataManager;
    private AttentionContract.View view;
    @Inject
    public AttentionPresenter(DataManager dataManager, AttentionContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }
    public void toGetAttentionAll(String token,int page) {
        Map map = new HashMap();
        map.put("token",token);
        map.put("pageNo",page);
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MY_ATTENTION_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"-----我的关注----------"+string);
                HttpResult result = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(result.getState(),"success")) {
                    if (TextUtils.equals(result.getCode(),"13000")) {
                        HttpResult<AttentionEntity> result2 = new Gson().fromJson(string,new TypeToken<HttpResult<AttentionEntity>>(){}.getType());
                        view.resultData(result2.getContent());
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
    public void toGetFansAll(String token,int page) {
        Map map = new HashMap();
        map.put("token",token);
        map.put("pageNo",page);
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MY_FANS_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"-----我的关注----------"+string);
                HttpResult result = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(result.getState(),"success")) {
                    if (TextUtils.equals(result.getCode(),"13000")) {
                        HttpResult<AttentionEntity> result2 = new Gson().fromJson(string,new TypeToken<HttpResult<AttentionEntity>>(){}.getType());
                        view.resultData(result2.getContent());
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
