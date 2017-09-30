package com.xzry.takeshow.entity.homeEntity;

import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;
import com.xzry.takeshow.entity.Brand;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.PageEntity;

import java.util.List;

/**  首页数据
 * @author：admin on 2017/3/28 14:18.
 */

public class HomeEntity implements MultiItemEntity{

    public static final int TYPE_TOP_BANNER = 1; //banner / iconList
    public static final int TYPE_BRAND_STORES = 2; //品牌门店
    public static final int TYPE_SELECTED_ACTIVITIES = 3; //精选活动
    public static final int TYPE_DAILY_SPECIALS = 4; //每日特价
    public static final int TYPE_SPECIAL_RECOMMOND = 5;//专题推荐
    public static final int TYPE_SELECT_THE_BRAND = 6;//品牌精选
    public static final int TYPE_BOTTOM_CONTENT = 7;//热门商品


    public int itemType;

    public HomeBannerEntity banner;
    public List<ActivitiesEntity> activities;
    public PageEntity<Goods> specials;
    public List<SpecialRecommondEntity> specialRecommond;
    public List<HotBrand> hotbrand;
    public PageEntity<Goods> hotgoods;

    public HomeEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public class HotBrand{
        public String id; //品牌ID
        public String logo;//品牌Logo
        public String address;//品牌详细地址
        public String name;//品牌名称
        public String goodsCount;//热门商品数量
        public List<Goods> goods;//商品列表
    }


}
