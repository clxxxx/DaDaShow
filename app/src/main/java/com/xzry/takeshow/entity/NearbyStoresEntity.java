package com.xzry.takeshow.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */


public class NearbyStoresEntity{
    public int currentPage;   //当前第几页
    public int pageCount;     //共多少页
    public int total;         //共多少条数据
    public int limit;         //每页显示多少条
    public int offset;
    public String orderName;
    public String page;
    public List<row> rows;
    public class row implements Serializable{
        public String distance; //距离
        public String storeID;     //门店ID
        public String storeCode;//门店编码
        public String address;
        public Double longitude;//门店经度
        public Double latitude;//门店纬度
        public String storeImg;//门店图片
        public String storeName;//门店名称
        public String mobile;   //电话
    }
}
