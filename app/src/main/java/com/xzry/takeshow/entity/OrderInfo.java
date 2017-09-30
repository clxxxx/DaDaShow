package com.xzry.takeshow.entity;

import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;
import com.xzry.takeshow.entity.commodity.Goods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 周东阳 on 2017/9/7 0007.
 */

public class OrderInfo implements MultiItemEntity, Serializable {

    public String orderId;//订单号
    public String buyerId;//买家ID
    public String orderNote;//订单备注
    public String tradeOrderDate;//下单日期yyyy-MM-dd HH:mm:ss
    public int paymentTypeId;//支付方式id
    public String paymentType;//支付方式
    public String orderTotalAmount;//订单总金额
    public String orderDiscount;//订单优惠金额
    public int integral;//消费积分值
    public String actualAmount;//实际支付金额
    public String consignee;//收货人的姓名
    public String country;//收货人的国家，默认CN
    public String provinceId;//省份ID
    public String province;//收货人的省份
    public String cityId;//城市ID
    public String city;//收货人的城市
    public String districtId;//地区ID
    public String district;//收货人的地区
    public String address;//收货人的详细地址
    public String zipcode;//收货人的邮编
    public String tel;//收货人的电话
    public String mobile;//收货人的手机
    public String email;//收货人的邮箱
    public String bestTme;//收货人的最佳送货时间
    public String orderStatusKey;//订单状态值
    public String orderStatus;//订单状态值
    public String orderVerificationCode;//提货码
    public int logisticsType;//物流方式。0：快递方式，1：上门自提（如果为自提单，则生成提货码）
    public String dueTime;//使用期限，下单日期后15天内到期
    public String appointmentTime;//预约时间，下单日期后7天内用户可选时间
    public String takeUser;//提货人
    public String takeMobile;//手机号
    public String buyNow;//是否一键购买。0：否，1：是。不是一键购买，支付后会清空购物车内相关商品信息。
    public String storeID;//门店ID
    public String storeName;//门店名
    public String storeAddress;//门店地址
    public String contactsMobile;//门店联系电话
    public String orderCountDownTime;//订单倒计时时间
    public List<Goods> goodsInfos;//商品列表
    public int state;//订单状态。，1：待付款，2：待收获，3：待评价，4：待发货

    @Override
    public int getItemType() {
        return state;
    }
}
