package com.xzry.takeshow.entity;

import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;

/**
 * Created by 周东阳 on 2017/8/8 0008.
 */

public class RecommendEntity implements MultiItemEntity {

    public static final int TOPIC = 0;
    public static final int IMG_SIZE_ONE = 1;
    public static final int IMG_SIZE_TWO = 2;
    public static final int IMG_SIZE_NINE = 9;

    private int itemType;

    public RecommendEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
