package com.xzry.takeshow.entity;

/**
 * @author: luosy
 * @date: 2017-9-4
 * @description:
 */


public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
