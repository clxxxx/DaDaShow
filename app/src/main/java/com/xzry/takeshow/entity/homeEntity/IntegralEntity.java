package com.xzry.takeshow.entity.homeEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-8-24
 * @description:
 */


public class IntegralEntity {
    public int currentPage;
    public int pageCount;
    public int total;
    public int limit;
    public int offset;
    public String orderName;
    public Boolean page;
    public List<item> rows;
    public class item{
        public int point;
        public String subName;
        public float marketPrice;
        public String spu;
        public float shopPrice;
        public String imageUrl;
        public String goodsName;
        public String sku;
        public int stock;
    }
}
