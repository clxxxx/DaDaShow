package com.xzry.takeshow.entity.request;

import java.util.List;

/**
 * Created by 周东阳 on 2017/9/18 0018.
 */

public class OrderRequest {

    private String orderNote;                           //订单备注
    private String paymentTypeId;                       //支付方式id，接口31(支付方式查询)
    private String paymentType;                         //支付方式，接口31(支付方式查询)
    private String orderTotalAmount;                    //订单总金额
    private String orderDiscount;                       //订单优惠金额
    private String integral;                            //消费积分值
    private String actualAmount;                        //实际支付金额
    private String consignee;                           //收货人的姓名
    private String provinceId;                          //省份ID
    private String province;                            //收货人的省份
    private String cityId;                              //城市ID
    private String city;                                //收货人的城市
    private String districtId;                          //地区ID
    private String district;                            //收货人的地区
    private String address;                             //收货人的详细地址
    private String zipcode;                             //收货人的邮编
    private String tel;                                 //收货人的电话
    private String mobile;                              //收货人的手机
    private String bestTime;                            //收货人的最佳送货时间
    private String buyNow;                              //是否一键购买。0：否，1：是。不是一键购买，支付后会清空购物车内相关商品信息。
    private List<Item> items;                               //订单备注
    private List<Stake> stake;                           //订单备注

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(String orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(String orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBestTime() {
        return bestTime;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public String getBuyNow() {
        return buyNow;
    }

    public void setBuyNow(String buyNow) {
        this.buyNow = buyNow;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Stake> getStake() {
        return stake;
    }

    public void setStake(List<Stake> stake) {
        this.stake = stake;
    }

    @Override
    public String toString() {
        return "{" +
                "orderNote='" + orderNote + '\'' +
                ", paymentTypeId='" + paymentTypeId + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", orderTotalAmount='" + orderTotalAmount + '\'' +
                ", orderDiscount='" + orderDiscount + '\'' +
                ", integral='" + integral + '\'' +
                ", actualAmount='" + actualAmount + '\'' +
                ", consignee='" + consignee + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", province='" + province + '\'' +
                ", cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", districtId='" + districtId + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bestTime='" + bestTime + '\'' +
                ", buyNow='" + buyNow + '\'' +
                ", items=" + items +
                ", stake=" + stake +
                '}';
    }

    public static class Item{
        private String storeId;                             //门店ID
        private String sku;                                 //平台SKU
        private String storeSku;                            //门店SKU
        private String spu;                                 //商品spu
        private String itemName;                            //商品的名称
        private String itemNum;                             //商品数量
        private String itemPrice;                           //商品原单价
        private String itemPriceNow;                        //商品原单价
        private String itemModel;                           //商品型号
        private String itemDiscount;                        //商品优惠金额，无优惠值为0
        private String orderDiscountTypeId;                 //优惠方式id，活动id
        private String orderDiscountType;                   //优惠方式，活动名称
        private String itemDiscountRate;                    //折扣率
        private String couponId;                            //优惠券id
        private String itemColor;                           //颜色
        private String itemSize;                            //尺码

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getStoreSku() {
            return storeSku;
        }

        public void setStoreSku(String storeSku) {
            this.storeSku = storeSku;
        }

        public String getSpu() {
            return spu;
        }

        public void setSpu(String spu) {
            this.spu = spu;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemNum() {
            return itemNum;
        }

        public void setItemNum(String itemNum) {
            this.itemNum = itemNum;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(String itemPrice) {
            this.itemPrice = itemPrice;
        }

        public String getItemPriceNow() {
            return itemPriceNow;
        }

        public void setItemPriceNow(String itemPriceNow) {
            this.itemPriceNow = itemPriceNow;
        }

        public String getItemModel() {
            return itemModel;
        }

        public void setItemModel(String itemModel) {
            this.itemModel = itemModel;
        }

        public String getItemDiscount() {
            return itemDiscount;
        }

        public void setItemDiscount(String itemDiscount) {
            this.itemDiscount = itemDiscount;
        }

        public String getOrderDiscountTypeId() {
            return orderDiscountTypeId;
        }

        public void setOrderDiscountTypeId(String orderDiscountTypeId) {
            this.orderDiscountTypeId = orderDiscountTypeId;
        }

        public String getOrderDiscountType() {
            return orderDiscountType;
        }

        public void setOrderDiscountType(String orderDiscountType) {
            this.orderDiscountType = orderDiscountType;
        }

        public String getItemDiscountRate() {
            return itemDiscountRate;
        }

        public void setItemDiscountRate(String itemDiscountRate) {
            this.itemDiscountRate = itemDiscountRate;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getItemColor() {
            return itemColor;
        }

        public void setItemColor(String itemColor) {
            this.itemColor = itemColor;
        }

        public String getItemSize() {
            return itemSize;
        }

        public void setItemSize(String itemSize) {
            this.itemSize = itemSize;
        }

        @Override
        public String toString() {
            return "{" +
                    "storeId='" + storeId + '\'' +
                    ", sku='" + sku + '\'' +
                    ", storeSku='" + storeSku + '\'' +
                    ", spu='" + spu + '\'' +
                    ", itemName='" + itemName + '\'' +
                    ", itemNum='" + itemNum + '\'' +
                    ", itemPrice='" + itemPrice + '\'' +
                    ", itemPriceNow='" + itemPriceNow + '\'' +
                    ", itemModel='" + itemModel + '\'' +
                    ", itemDiscount='" + itemDiscount + '\'' +
                    ", orderDiscountTypeId='" + orderDiscountTypeId + '\'' +
                    ", orderDiscountType='" + orderDiscountType + '\'' +
                    ", itemDiscountRate='" + itemDiscountRate + '\'' +
                    ", couponId='" + couponId + '\'' +
                    ", itemColor='" + itemColor + '\'' +
                    ", itemSize='" + itemSize + '\'' +
                    '}';
        }
    }

    public static class Stake{
        private String storeId;                             //门店ID
        private String logisticsType;                       //物流方式。0：快递方式，1：上门自提
        private String appointmentTime;                     //预约时间，下单日期后7天内用户可选时间
        private String takeUser;                            //提货人
        private String takeMobile;                          //手机号

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getLogisticsType() {
            return logisticsType;
        }

        public void setLogisticsType(String logisticsType) {
            this.logisticsType = logisticsType;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public String getTakeUser() {
            return takeUser;
        }

        public void setTakeUser(String takeUser) {
            this.takeUser = takeUser;
        }

        public String getTakeMobile() {
            return takeMobile;
        }

        public void setTakeMobile(String takeMobile) {
            this.takeMobile = takeMobile;
        }

        @Override
        public String toString() {
            return "{" +
                    "storeId='" + storeId + '\'' +
                    ", logisticsType='" + logisticsType + '\'' +
                    ", appointmentTime='" + appointmentTime + '\'' +
                    ", takeUser='" + takeUser + '\'' +
                    ", takeMobile='" + takeMobile + '\'' +
                    '}';
        }
    }

}
