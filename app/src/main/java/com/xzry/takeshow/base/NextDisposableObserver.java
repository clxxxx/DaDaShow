package com.xzry.takeshow.base;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sxjs.common.GlobalAppComponent;
import com.sxjs.common.base.rxjava.SubscriberOnNextListener;
import com.sxjs.common.model.http.HttpResult;
import com.sxjs.common.model.http.NoNetWorkException;

import java.io.IOException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/9/25 0025.
 */

public class NextDisposableObserver extends DisposableObserver<ResponseBody> {

    private SubscriberOnNextListener mSubscriberOnNextListener;

    public NextDisposableObserver(SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        String result = null;
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpResult httpResult = new Gson().fromJson(result, HttpResult.class);
        if (mSubscriberOnNextListener != null) {
            switch (httpResult.getCode()){
                case 13000:
                    mSubscriberOnNextListener.onNext(result);
                    break;
                case 12000:
                    mSubscriberOnNextListener.onError(httpResult.getCode());
                    break;
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        //此处可按状态码解析或error类型，进行分别处理其他error,此处只处理一种
        if(e instanceof NoNetWorkException){
            Toast.makeText(GlobalAppComponent.getAppComponent().getContext(), "网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete() {

    }
}