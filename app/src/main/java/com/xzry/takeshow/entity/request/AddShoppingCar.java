package com.xzry.takeshow.entity.request;

/**
 * Created by 周东阳 on 2017/9/11 0011.
 */

public class AddShoppingCar {

    private String storeId;
    private String sku;
    private String spu;
    private String storeSku;
    private String itemColor;
    private String itemSize;
    private String itemName;
    private int itemNum;
    private String itemPrice;
    private String itemPriceNow;
    private String itemModel;
    private String itemDiscount;
    private String orderDiscountTypeId;
    private String orderDiscountType;
    private String itemDiscountRate;
    private String couponId;

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

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getStoreSku() {
        return storeSku;
    }

    public void setStoreSku(String storeSku) {
        this.storeSku = storeSku;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
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

    @Override
    public String toString() {
        return "{" +
                "storeId='" + storeId + '\'' +
                ", sku='" + sku + '\'' +
                ", spu='" + spu + '\'' +
                ", storeSku='" + storeSku + '\'' +
                ", itemColor='" + itemColor + '\'' +
                ", itemSize='" + itemSize + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemNum=" + itemNum +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemPriceNow='" + itemPriceNow + '\'' +
                ", itemModel='" + itemModel + '\'' +
                ", itemDiscount='" + itemDiscount + '\'' +
                ", orderDiscountTypeId='" + orderDiscountTypeId + '\'' +
                ", orderDiscountType='" + orderDiscountType + '\'' +
                ", itemDiscountRate='" + itemDiscountRate + '\'' +
                ", couponId='" + couponId + '\'' +
                '}';
    }
}
