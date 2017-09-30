package com.xzry.takeshow.entity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-22
 * @description:
 */


public class CollectMerchant {
    public String currentPage;
    public String pageCount;
    public String total;
    public String limit;
    public String offset;
    public String orderName;
    public String page;
    public List<Item> rows;
    public class Item{
        public String id;
        public String storeName;
        public String storeImg;
        public String isfollow;     //是否关注，true：关注，false：未关注
        public String goodsCount;   //门店下的商品总数
    }

}
