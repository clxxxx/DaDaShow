package com.xzry.takeshow.entity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-14
 * @description:
 */


public class HttpResults<T> {
    private String state;

    private String code;

    private List<T> content;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
