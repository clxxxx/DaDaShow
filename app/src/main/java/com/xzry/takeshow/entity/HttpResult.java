package com.xzry.takeshow.entity;

/**
 * Created by zdy on 16/3/5.
 */
public class HttpResult<T> {

    private String state;

    private T content;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
