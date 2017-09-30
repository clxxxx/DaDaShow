package com.xzry.takeshow.entity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */


public class MyIntegralEntity {
    public String currentPage;  //当前第几页
    public String pageCount;    //共多少页
    public String total;        //共多少条数据
    public String limit;        //每页显示多少条
    public String offset;
    public String orderName;
    public Boolean page;
    public List<Item> rows;
    public class Item{
        public String point;    //积分信息
        public String des;      //描述
        public String orderNo;  //订单号
        public String createDate;//积分获取使用时间
        public String type;
        public String integral;
    }
}
