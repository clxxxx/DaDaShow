package com.xzry.takeshow.ui.mine.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.umeng.socialize.utils.Log;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.AddressEntity;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.ui.mine.contract.AddressContract;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */


public class AddressPresenter extends BasePresenter implements AddressContract.Presenter {
    String TAG = "address";
    private DataManager mDataManager;
    private AddressContract.View view;
    @Inject
    public AddressPresenter(DataManager dataManager, AddressContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }
    /**
     *  查询所有
     */
    @Override
    public void toGetAddress(Map map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_ADDRESS_SHOW,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult result = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(result.getState(),"success")) {
                    if (TextUtils.equals(result.getCode(),"13000")) {
                        HttpResult<List<AddressEntity>> result2 = new Gson().fromJson(string,new TypeToken<HttpResult<List<AddressEntity>>>(){}.getType());
                        view.resultData(result2.getContent());
                    }
                }else {

                }
                Log.i(TAG,"---------------"+string);
            }
            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }
    /**
     *  添加收货地址
     */
    @Override
    public void toAddAddress(final Map map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.ADD_ADDRESS_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult result = new Gson().fromJson(string,new TypeToken<HttpResult>(){}.getType());
                Log.i(TAG,"---------------"+string);
                if (result.getState().equals("success")) {
                    view.refreshDisplay("success");


                }else {

                }
            }

            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }
    /**
     *  删除收货地址
     */

    public void toDelAddress(final Map map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.DEL_ADDRESS_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult result = new Gson().fromJson(string,new TypeToken<HttpResult>(){}.getType());
                Log.i(TAG,"---------------"+string);
                if (result.getState().equals("success")) {
                    view.refreshDisplay("success");


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
