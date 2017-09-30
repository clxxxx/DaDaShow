package com.xzry.takeshow.entity;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by 周东阳 on 2017/9/6 0006.
 */

public class RequestEvent {

    public static final int ADDSHOPPINGCAR = 1001;
    public static final int CHECKSTOCK = 1002;
    public static final int COLLECT = 1003;

    private int type;
    private Map<String, String> map;
    private DisposableObserver<ResponseBody> consumer;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public DisposableObserver<ResponseBody> getConsumer() {
        return consumer;
    }

    public void setConsumer(DisposableObserver<ResponseBody> consumer) {
        this.consumer = consumer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
