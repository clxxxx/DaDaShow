package com.xzry.takeshow.entity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */


public class AttentionEntity {
    public String currentPage;  //当前第几页
    public String pageCount;    //共多少页
    public String total;        //共多少条数据
    public String limit;        //每页显示多少条
    public String offset;
    public String orderName;
    public Boolean page;
    public List<Item> rows;
    public class Item{
        public String id;
        public String headerUrl;
        public String nickname;
        public String fansCount;    //用户粉丝数
        public String dynCount;     //用户动态数
        public String followStatus; //关注状态，1: 互相关注，3: 他单方面关注你
    }
}
