package com.xzry.takeshow.entity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/9/1 0001.
 */

public class PageEntity<T> {

    public int currentPage; //总页数
    public List<T> rows;
    public boolean page;
    public String orderName;
    public int pageCount;//总条数
    public int limit;
    public int total;
    public String order;
    public int offset;

}
