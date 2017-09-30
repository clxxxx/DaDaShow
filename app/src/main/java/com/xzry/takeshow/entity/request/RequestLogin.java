package com.xzry.takeshow.entity.request;

import java.util.Map;

/**
 * @author: luosy
 * @date: 2017-8-31
 * @description:
 */


public class RequestLogin  {
    private String mobile;  //手机
    private String loginType;   //100token登录，200第三方登录，300验证码登录，必填
    private String code;    //token登录
    private String sourceAppid;//第三方标示
    private String verifyCode;   //验证码
    private String deviceNumber; //客户端标示
    private String sex;            //性别
    private String headerUrl;      //头像

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSourceAppid() {
        return sourceAppid;
    }

    public void setSourceAppid(String sourceAppid) {
        this.sourceAppid = sourceAppid;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }
}
