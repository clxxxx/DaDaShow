package com.xzry.takeshow.entity;

import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class OrderEntity implements MultiItemEntity {


    public static final int ORDER_STATE_WAIT_DELIVER = 0;//待付款
    public static final int ORDER_STATE_WAIT_OBLIGATION = 1;//待发货
    public static final int ORDER_STATE_WAIT_OBLIGATION_ZITI = 2;//待发货——自提
    public static final int ORDER_STATE_WAIT_RECEIVING = 3;//待收货
    public static final int ORDER_STATE_WAIT_EVALUATE = 4;//待评价
    public static final int ORDER_STATE_WAIT_EVALUATE_ZITI = 5;//待评价——自提

    public List<OrderInfo> orderInfos; //门店联系电话

    private int itemType;

    public OrderEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
