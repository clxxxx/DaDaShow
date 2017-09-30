package com.xzry.takeshow.ui.homepager.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseMultiItemQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.util.GlideImageLoader;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.homeEntity.HomeEntity;
import com.xzry.takeshow.ui.WebViewActivity;
import com.xzry.takeshow.ui.commodityInfo.CommodityInfoActivity;
import com.xzry.takeshow.ui.eventtopic.EventTopicActivity;
import com.xzry.takeshow.ui.homepager.AllCommdityClassifyActivity;
import com.xzry.takeshow.ui.homepager.BrandActivity;
import com.xzry.takeshow.ui.homepager.BrandStreetActivity;
import com.xzry.takeshow.ui.homepager.NearbyStoresListActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import static com.xzry.takeshow.R.id.activity_img1;

/**
 * @author: luosy
 * @date: 2017-8-3
 * @description:
 */


public class HomeMultipleRecycleAdapter extends BaseMultiItemQuickAdapter<HomeEntity, BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener{

    public HomeMultipleRecycleAdapter() {
        addItemType(HomeEntity.TYPE_TOP_BANNER, R.layout.homerecycle_item_top_banner);
        addItemType(HomeEntity.TYPE_SELECTED_ACTIVITIES, R.layout.item_home_selected_activities);
        addItemType(HomeEntity.TYPE_DAILY_SPECIALS, R.layout.item_home_daily_specials);
        addItemType(HomeEntity.TYPE_SPECIAL_RECOMMOND, R.layout.item_home_special_recommond);
        addItemType(HomeEntity.TYPE_SELECT_THE_BRAND, R.layout.item_home_select_the_brand);
        addItemType(HomeEntity.TYPE_BOTTOM_CONTENT, R.layout.item_hot_goods);
    }

    /**
     * 数据绑定未进行详细的数据验证，在实际使用中不可取
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     * @param position
     */
    @Override
    protected void convert(BaseViewHolder helper, HomeEntity item, int position) {
        switch (helper.getItemViewType()) {
            case HomeEntity.TYPE_TOP_BANNER:
                bindTopBannerData(helper, item, position);
                break;
            case HomeEntity.TYPE_SELECTED_ACTIVITIES:
                bindSelectedActivitise(helper, item, position);
                break;
            case HomeEntity.TYPE_DAILY_SPECIALS:
                bindDailySpecials(helper, item, position);
                break;
            case HomeEntity.TYPE_SPECIAL_RECOMMOND:
                bindSpecialsRecommond(helper, item, position);
                break;
            case HomeEntity.TYPE_SELECT_THE_BRAND:
                bindHotBrand(helper, item, position);
                break;
            case HomeEntity.TYPE_BOTTOM_CONTENT:
                bindHotGoods (helper, item, position);
                break;
        }
        setOnItemChildClickListener(this);
    }

    private void bindHotGoods(final BaseViewHolder helper, HomeEntity item, int position) {
        RecyclerView hotgoods = helper.getView(R.id.hotgoods_recyclerView);
        hotgoods.setFocusable(false);
        hotgoods.setLayoutManager(new GridLayoutManager(mContext, 2));
        HotGoodsAdapter adapter = new HotGoodsAdapter(item.hotgoods.rows);
        hotgoods.setAdapter(adapter);
    }

    private void bindHotBrand(final BaseViewHolder helper, final HomeEntity item, int position) {
        ViewPager viewpager = helper.getView(R.id.hot_brand_viewpager);
        viewpager.setFocusable(false);
        List<View> viewList = new ArrayList<>();
        for (int i=0; i<item.hotbrand.size(); i++){
            View brandinfo = LayoutInflater.from(mContext).inflate(R.layout.item_hot_brand, null);
            ((ExpandImageView)brandinfo.findViewById(R.id.brand_log)).setImageURI(item.hotbrand.get(i).logo);
            TextView brandname = (TextView) brandinfo.findViewById(R.id.brand_name);
            brandname.setText(item.hotbrand.get(i).name);
            TextView goodsCount = (TextView) brandinfo.findViewById(R.id.goodsCount);
            goodsCount.setText(item.hotbrand.get(i).goodsCount);
            final int p = i;
            if (item.hotbrand.get(p).goods != null)
            {
                if (item.hotbrand.get(p).goods.size() > 0){
                    ((ExpandImageView) brandinfo.findViewById(R.id.goods_img1)).setImageURI(item.hotbrand.get(p).goods.get(0).imageUrl);
                    ((TextView) brandinfo.findViewById(R.id.goods_price1)).setText("￥" + item.hotbrand.get(p).goods.get(0).shopPrice);
                    brandinfo.findViewById(R.id.goods_img1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommodityInfoActivity.intentCommodityInfoActivity(mContext, item.hotbrand.get(p).goods.get(0).spu);
                        }
                    });
                }
                if (item.hotbrand.get(i).goods.size() > 1){
                    ((ExpandImageView) brandinfo.findViewById(R.id.goods_img2)).setImageURI(item.hotbrand.get(p).goods.get(1).imageUrl);
                    ((TextView) brandinfo.findViewById(R.id.goods_price2)).setText("￥" + item.hotbrand.get(p).goods.get(1).shopPrice);
                    brandinfo.findViewById(R.id.goods_img2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommodityInfoActivity.intentCommodityInfoActivity(mContext, item.hotbrand.get(p).goods.get(1).spu);
                        }
                    });
                }
                if (item.hotbrand.get(i).goods.size() > 2){
                    ((ExpandImageView) brandinfo.findViewById(R.id.goods_img3)).setImageURI(item.hotbrand.get(p).goods.get(2).imageUrl);
                    ((TextView) brandinfo.findViewById(R.id.goods_price3)).setText("￥" + item.hotbrand.get(p).goods.get(2).shopPrice);
                    brandinfo.findViewById(R.id.goods_img3).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommodityInfoActivity.intentCommodityInfoActivity(mContext, item.hotbrand.get(p).goods.get(2).spu);
                        }
                    });
                }
            }
            viewList.add(brandinfo);
            final int finalI = i;
            brandinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BrandActivity.intentActivity(mContext, item.hotbrand.get(finalI).id);
                }
            });
        }
        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(viewList);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setPageMargin(ScreenUtil.dip2px(mContext, 10));
    }

    private void bindSpecialsRecommond(final BaseViewHolder helper, HomeEntity item, int position) {
        RecyclerView specials = helper.getView(R.id.recommond_recyclerView);
        specials.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        SpecialRecommondAdapter adapter = new SpecialRecommondAdapter(item.specialRecommond);
        specials.setAdapter(adapter);
        specials.setFocusable(false);
    }

    private void bindDailySpecials(final BaseViewHolder helper, HomeEntity item, int position) {
        RecyclerView recyclerView = helper.getView(R.id.specials_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        SpecialsAdapter adapter = new SpecialsAdapter(item.specials.rows);
        recyclerView.setAdapter(adapter);
        recyclerView.setFocusable(false);

    }

    private void bindSelectedActivitise(final BaseViewHolder helper, final HomeEntity item, int position) {
        if (item.activities == null)
            return;
        if (item.activities.size() > 0){
            ((ExpandImageView) helper.getView(activity_img1)).setImageURI(item.activities.get(0).imageUrl);
            helper.addOnClickListener(activity_img1);
        }
        if (item.activities.size() > 1){
            ((ExpandImageView) helper.getView(R.id.activity_img2)).setImageURI(item.activities.get(1).imageUrl);
            helper.addOnClickListener(R.id.activity_img2);
        }
        if (item.activities.size() > 2){
            ((ExpandImageView) helper.getView(R.id.activity_img3)).setImageURI(item.activities.get(2).imageUrl);
            helper.addOnClickListener(R.id.activity_img3);
        }
    }

    private void bindTopBannerData(final BaseViewHolder helper, final HomeEntity item, int position) {
        List<String> images = new ArrayList<>();
        for (int i=0; i < item.banner.banner.size(); i++ ){
            images.add(item.banner.banner.get(i).imageUrl);
        }
        Banner banner = helper.getView(R.id.home_banner);

        banner.setImages(images);
        banner.setBannerAnimation(Transformer.Default);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        banner.setImageLoader(new GlideImageLoader());
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                setEventClickListener(item.banner.banner.get(position).imageLinkType, item.banner.banner.get(position).imageLinkID);
            }
        });
        banner.start();
        helper.addOnClickListener(R.id.home_icon_lay1);
        helper.addOnClickListener(R.id.home_icon_lay2);
        helper.addOnClickListener(R.id.home_icon_lay3);
        helper.addOnClickListener(R.id.home_icon_lay4);
        helper.addOnClickListener(R.id.home_icon_lay5);

        if (item.banner.storeInfo != null){
            helper.setVisible(R.id.banner_store, true);
            helper.setText(R.id.home_bs_name, item.banner.storeInfo.get(0).storeName);
            List<Goods> goods = item.banner.storeInfo.get(0).goods;
            if (goods != null)
            {
                if (goods.size() > 0){
                    ((ExpandImageView) helper.getView(R.id.goods_img1)).setImageURI(goods.get(0).imageUrl);
                    helper.setText(R.id.goods_price1, goods.get(0).shopPrice);
                    helper.addOnClickListener(R.id.goods_img1);
                }
                if (goods.size() > 1){
                    ((ExpandImageView) helper.getView(R.id.goods_img2)).setImageURI(goods.get(1).imageUrl);
                    helper.setText(R.id.goods_price2, goods.get(1).shopPrice);
                    helper.addOnClickListener(R.id.goods_img2);
                }
                if (goods.size() > 2){
                    ((ExpandImageView) helper.getView(R.id.goods_img3)).setImageURI(goods.get(2).imageUrl);
                    helper.setText(R.id.goods_price3, goods.get(2).shopPrice);
                    helper.addOnClickListener(R.id.goods_img3);
                }
            }
        }


    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.home_icon_lay1:       //特卖
                EventTopicActivity.toActivity(mContext, "活动专题", mData.get(0).banner.icon.get(position).imageLinkID);
                break;
            case R.id.home_icon_lay2:       //门店
                NearbyStoresListActivity.toActivity(mContext);
                break;
            case R.id.home_icon_lay3:       //品牌街
                BrandStreetActivity.toActivity(mContext);
                break;
            case R.id.home_icon_lay4:       //精选
                // TODO 不知道干啥
                break;
            case R.id.home_icon_lay5:       //分类
                AllCommdityClassifyActivity.toActivity(mContext);
                break;
            case R.id.home_bs_linlayout:    //品牌门店
                //BrandShopActivity.toActivity(mContext);
                break;
            case R.id.goods_img1:
                CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).banner.storeInfo.get(0).goods.get(0).spu);
                break;
            case R.id.goods_img2:
                CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).banner.storeInfo.get(0).goods.get(1).spu);
                break;
            case R.id.goods_img3:
                CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).banner.storeInfo.get(0).goods.get(2).spu);
                break;
            case R.id.activity_img1:
                setEventClickListener(mData.get(1).activities.get(0).imageLinkType, mData.get(1).activities.get(0).imageLinkID);
                break;
            case R.id.activity_img2:
                setEventClickListener(mData.get(1).activities.get(1).imageLinkType, mData.get(1).activities.get(1).imageLinkID);
                break;
            case R.id.activity_img3:
                setEventClickListener(mData.get(1).activities.get(2).imageLinkType, mData.get(1).activities.get(2).imageLinkID);
                break;
        }
        return false;
    }

    private void setEventClickListener(int imageLinkType, String imageLinkID){
        switch (imageLinkType)
        {
            case 0:
                EventTopicActivity.toActivity(mContext, "活动专题", imageLinkID);
                break;
            case 1:
                EventTopicActivity.toActivity(mContext, "活动专题", imageLinkID);
                break;
            case 2:
                WebViewActivity.initActivity(mContext, imageLinkID);
                break;
            case 3:
                CommodityInfoActivity.intentCommodityInfoActivity(mContext, imageLinkID);
                break;
            case 4:
                AllCommdityClassifyActivity.toActivity(mContext);
                break;
            case 5:
                // TODO 门店

                break;
            case 6:
                EventTopicActivity.toActivity(mContext, "活动专题", imageLinkID);
                break;
            case 7:
                BrandStreetActivity.toActivity(mContext);
                break;
        }
    }
}
