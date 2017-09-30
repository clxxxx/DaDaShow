package com.xzry.takeshow.entity;

import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-16
 * @description:
 */


public class BrandShopEntity implements MultiItemEntity{
    public static final int TYPE_NEW_PRODUCT = 1; //新品
    public static final int TYPE_HOT = 2; //热门
    public static final int TYPE_ALL = 3; //推荐

    public storeInfo storeInfo;
    public List<item> goodsNew;
    public List<item> recommend;
    public boolean follow;

    public int itemType;


    public class storeInfo{
        public String id;
        public String storeCode;
        public String address;
        public String longitude;
        public String latitude;
        public String storeImg;
        public String storeName;
    }
    public class item{
        public String subName;
        public String spu;
        public String shopPrice;
        public String imageUrl;
        public String goodsName;
        public String sku;
    }
    public BrandShopEntity(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {
        return itemType;
    }
}
