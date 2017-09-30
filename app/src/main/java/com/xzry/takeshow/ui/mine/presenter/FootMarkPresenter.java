package com.xzry.takeshow.ui.mine.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.FootMarkEntity;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.ui.mine.contract.FootMarkContract;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */


public class FootMarkPresenter extends BasePresenter implements FootMarkContract.Presenter{
    private DataManager mDataManager;
    private FootMarkContract.View view;
    @Inject
    public FootMarkPresenter(DataManager dataManager, FootMarkContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }

    @Override
    public void getFootMarkList(Map map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MY_FOOT_MARK_LIST_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                HttpResult http = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(http.getState(),"success")) {
                    if (TextUtils.equals(http.getCode(),"13000")) {
                        HttpResult<FootMarkEntity> result = new Gson().fromJson(string,new TypeToken<HttpResult<FootMarkEntity>>(){}.getType());
                        view.getListSuccess(result.getContent());
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
    @Override
    public void getDetele(Map map) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_DELETE_MY_FOOT_MARK_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                HttpResult http = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(http.getState(),"success")) {
                    if (TextUtils.equals(http.getCode(),"13000")) {
                        view.getDeleteSuccess();
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
