package com.xzry.takeshow.entity.commodity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周东阳 on 2017/9/2 0002.
 */

public class StoreInfo implements Serializable {

    public String storeName; //门店名称
    public String storeImg; //门店名称
    public String address; //详细地址
    public String storeID; //门店ID
    public String longitude; //精度
    public String latitude; //维度
    public String storePhoneNum; //门店联系电话
    public ArrayList<Goods> shoppingCarGoods; //门店商品

    public List<Goods> goods; //门店商品

    public int logisticsType; //确认订单是的自提方式，//0是快递， 1是门店自提
    public String consigneeName; //提货人姓名
    public String consigneeMobile;//提货人电话
    public String time;//提货时间


}
