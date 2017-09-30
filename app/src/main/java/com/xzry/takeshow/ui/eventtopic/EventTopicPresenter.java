package com.xzry.takeshow.ui.eventtopic;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.HttpResults;
import com.xzry.takeshow.entity.homeEntity.EventTopicComp;
import com.xzry.takeshow.entity.homeEntity.EventTopicEntity;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-15
 * @description:
 */


public class EventTopicPresenter extends BasePresenter implements EventtopicContract.Presenter{
    private DataManager mDataManager;
    private EventtopicContract.View view;
    @Inject
    public EventTopicPresenter(DataManager dataManager,EventtopicContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }

    public void getAllData(String int1) {
        Disposable disposable = mDataManager.getRequestData(BaseValue.HOME_ENENT_TOPIC_MAIN_URL+"/"+int1,new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResults httpResult = new Gson().fromJson(string,HttpResults.class);
                if (TextUtils.equals(httpResult.getState(),"success")) {
                    if (TextUtils.equals(httpResult.getCode(),"13000")) {
                        HttpResults<EventTopicComp> result = new Gson().fromJson(string,new TypeToken<HttpResults<EventTopicComp>>(){}.getType());
                        view.getResult(result.getContent());
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
    public void getAllInfo(int int1,int int2,int int3){
        Disposable disposable = mDataManager.getRequestData(BaseValue.ENENT_TOPIC_INFO_URL+"/"+int1+"/"+int2+"/"+int3,new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResult httpResult = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(httpResult.getState(),"success")) {
                    if (TextUtils.equals(httpResult.getCode(),"13000")) {
                        HttpResult<EventTopicEntity> result = new Gson().fromJson(string,new TypeToken<HttpResult<EventTopicEntity>>(){}.getType());
                        view.getResultChild(result.getContent());
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
