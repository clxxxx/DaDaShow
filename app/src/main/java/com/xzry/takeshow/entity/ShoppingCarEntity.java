package com.xzry.takeshow.entity;

import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;
import com.xzry.takeshow.entity.commodity.StoreInfo;

/**
 * Created by 周东阳 on 2017/8/19 0019.
 */

public class ShoppingCarEntity implements MultiItemEntity {

    public static final int INVALID = 0; //失效
    public static final int EFFECTIVE = 1; //有效

    public StoreInfo storeInfo;
    public int itemType;

    public ShoppingCarEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
