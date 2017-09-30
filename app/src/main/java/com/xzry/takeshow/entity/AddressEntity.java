package com.xzry.takeshow.entity;

import java.io.Serializable;

/**
 * @author: luosy
 * @date: 2017-9-13
 * @description:
 */


public class AddressEntity implements Serializable{
    public String id;
    public String consignee;
    public String country;
    public int provinceId = 1;
    public String province;
    public int cityId =1;
    public String city;
    public int districtId =1;
    public String district;
    public String address;
    public String zipcode;
    public String tel;
    public String mobile;
    public String email;
    public String bestTime;

    public String userId;
    public int isDefault;
    @Override
    public String toString() {
        return "{" +
                "consignee='" + consignee + '\'' +
                ", country='" + country + '\'' +
                ", provinceId=" + provinceId +
                ", province='" + province + '\'' +
                ", cityId=" + cityId +
                ", city='" + city + '\'' +
                ", districtId=" + districtId +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", bestTime='" + bestTime + '\'' +
                ", id=" + id +
                ", isDefault=" + isDefault +
                '}';
    }
}
