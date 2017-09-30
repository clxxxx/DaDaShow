package com.xzry.takeshow.entity.response;

import java.util.List;

/** 搜索返回类
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */
public class SearchDataResponse {
    public int recordCount;
    public int pageCount;
    public int curPage;
    public List<DataBean> itemList;
    public class DataBean{
        public String Sku;
        public String storeSku;
        public String goodsName;
        public String shopPrice;
        public String goodsKeywords;
        public String shortDescription;
        public String imageUrl;
    }

}
