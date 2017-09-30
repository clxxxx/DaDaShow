package com.xzry.takeshow.entity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */


public class FootMarkEntity {
    public String currentPage;
    public String pageCount;
    public String total;
    public String limit;
    public String offset;
    public String orderName;
    public String page;
    public List<Item> rows;
    public class Item{
        public String marketPrice;
        public String spu;
        public String imageUrl;
        public String shopPrice;
        public String sku;
        public String goodsName;
        public boolean showStatus;
        public boolean selectStatus;
    }
}
