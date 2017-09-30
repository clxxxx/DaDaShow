package com.sxjs.common.model.http;

/**
 * Created by 周东阳 on 2017/9/25 0025.
 */

public class HttpResult<T> {

    private String state;

    private T content;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
