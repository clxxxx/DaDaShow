package com.xzry.takeshow.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 周东阳 on 2017/9/19 0019.
 */

public class PaymentEntity {

    public String sign;
    public String timestamp;
    public String partnerid;
    public String noncestr;
    public String orderNo;
    public String paymentTypeId;
    public String prepayid;
    @SerializedName("package")
    public String packaget;
    public String appid;

}
