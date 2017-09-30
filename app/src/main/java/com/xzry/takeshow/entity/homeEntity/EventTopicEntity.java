package com.xzry.takeshow.entity.homeEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-15
 * @description:
 */


public class EventTopicEntity {
    public int currentPage;
    public int pageCount;
    public int total;
    public int limit;
    public int offset;
    public String orderName;
    public Boolean page;
    public List<item> rows;
    public class item{
        public String subName;
        public String spu;
        public float shopPrice;
        public String imageUrl;
        public String goodsName;
        public String sku;
    }
}
