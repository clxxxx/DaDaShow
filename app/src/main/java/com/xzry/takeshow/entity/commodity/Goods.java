package com.xzry.takeshow.entity.commodity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 周东阳 on 2017/8/30 0030.
 */

public class Goods implements Serializable {

    public String id;           //商品ID
    public String spu;          //商品组合编号
    public String sku;          //商品编码
    public String goodsName;    //商品名称
    public String brandID;      //品牌ID
    public String imageUrl;     //商品图片
    public String shopPrice;    //商品售价
    public String follow;       //是否关注，当用户登录传入token时，此字段返回。否则不反悔
    public String color;        //商品颜色
    public List<String> banner; //商品banner图集合
    public String desc;         //属性详情
    public int isOnline;        //是否可以门店自提，0：可以，1：不可以
    public List<String> colors; //颜色集合
    public List<CommodityAttrs> attrs;//商品属性
    public List<String> sizes;  //尺寸集合
    public String size;         //尺寸
    public String subName;      //商品副标题
    public GoodsComment comment;//商品评价
    public StoreInfo storeInfo; //门店信息
    public int stock;           /**商品库存*/
    public String status;       //商品状态, Online: 上架, Offline: 下架
    public String subjectName;  //活动名称
    public String subjectID;    //活动id
    public String storeSku;     //门店SKU编码
    public String marketPrice;  //商品原售价

    public int itemNum;  //商品数量用于订单信息
    public int logisticsType;  //物流方式。0：快递方式，1：上门自提 用于订单信息
    public int itemState;  //0，失效，1有效
    public int itemDiscount;  //商品优惠金额，无优惠值为0
    public boolean selected = false;  //是否选中，购物车使用

    public class CommodityAttrs implements Serializable{
        public String attributeKey;
        public String attributeValue;
        public String id;
    }
}
