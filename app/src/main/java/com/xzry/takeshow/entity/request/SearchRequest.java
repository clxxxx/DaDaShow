package com.xzry.takeshow.entity.request;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */


public class SearchRequest {
    private String userId;
    private String keyWord;
    private String page;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
