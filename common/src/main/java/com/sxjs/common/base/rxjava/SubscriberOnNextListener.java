package com.sxjs.common.base.rxjava;

/**
 * Created by 周东阳 on 2017/9/25 0025.
 */

public interface SubscriberOnNextListener {

    void onNext(String result);

    void onError(int code);
}
