package com.xzry.takeshow.entity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-22
 * @description:
 */


public class CollectGoods {
    public Goods goods;
    public List<GoodsType> category;
    public class Goods{
        public String currentPage;
        public String pageCount;
        public String total;
        public String limit;
        public String offset;
        public String orderName;
        public String page;
        public List<GoodItem> rows;
        public class GoodItem{
            public String marketPrice;
            public String spu;
            public String imageUrl;
            public String shopPrice;
            public String sku;
            public String goodsName;
        }
    }
    public class  GoodsType{
        public String categoryName; //分类名称
        public String categoryID;   //分类ID
    }


}
