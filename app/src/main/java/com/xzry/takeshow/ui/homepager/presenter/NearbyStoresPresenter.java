package com.xzry.takeshow.ui.homepager.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.NearbyStoresEntity;
import com.xzry.takeshow.ui.homepager.contract.NearbyStoresContract;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */


public class NearbyStoresPresenter extends BasePresenter implements NearbyStoresContract.Presenter {
    private DataManager mDataManager;
    private NearbyStoresContract.View view;
    @Inject
    public NearbyStoresPresenter(DataManager dataManager,NearbyStoresContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }

    @Override
    public void toGetData(Map mp) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.HOME_NEARBY_STORES_URL,mp,new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    HttpResult<NearbyStoresEntity> result = new Gson().fromJson(string,new TypeToken<HttpResult<NearbyStoresEntity>>(){}.getType());
                    if (result.getState().equals("success")) {
                        view.resultData(result.getContent());
                    }else {

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }

}
