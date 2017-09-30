package com.xzry.takeshow.ui.homepager.adapter;

import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.BrandStreet;
import com.xzry.takeshow.ui.commodityInfo.CommodityInfoActivity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-8-21
 * @description:
 */


public class BrandStreetAdapter extends BaseQuickAdapter<BrandStreet,BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener{

    public BrandStreetAdapter(){
        super(R.layout.item_brand_street);
    }
    @Override
    protected void convert(BaseViewHolder helper, BrandStreet item, int position) {

        ((ExpandImageView) helper.getView(R.id.brand_log)).setImageURI(item.brandInfo.brandLogo);
        ((TextView) helper.getView(R.id.brand_name)).setText(item.brandInfo.brandName);
        ((TextView) helper.getView(R.id.brand_remark)).setText(item.brandInfo.brandRemark);

        ((ExpandImageView) helper.getView(R.id.goods_img1)).setImageURI("");
        ((TextView) helper.getView(R.id.goods_price1)).setText("");
        ((ExpandImageView) helper.getView(R.id.goods_img2)).setImageURI("");
        ((TextView) helper.getView(R.id.goods_price2)).setText("");
        ((ExpandImageView) helper.getView(R.id.goods_img3)).setImageURI("");
        ((TextView) helper.getView(R.id.goods_price3)).setText("");

        if (item.goods != null)
        {
            if (item.goods.size() > 0){
                ((ExpandImageView) helper.getView(R.id.goods_img1)).setImageURI(item.goods.get(0).imageUrl);
                ((TextView) helper.getView(R.id.goods_price1)).setText("￥" + item.goods.get(0).shopPrice);
                helper.addOnClickListener(R.id.goods_img1);
            }
            if (item.goods.size() > 1){
                ((ExpandImageView) helper.getView(R.id.goods_img2)).setImageURI(item.goods.get(1).imageUrl);
                ((TextView) helper.getView(R.id.goods_price2)).setText("￥" + item.goods.get(1).shopPrice);
                helper.addOnClickListener(R.id.goods_img2);
            }
            if (item.goods.size() > 2){
                ((ExpandImageView) helper.getView(R.id.goods_img3)).setImageURI(item.goods.get(2).imageUrl);
                ((TextView) helper.getView(R.id.goods_price3)).setText("￥" + item.goods.get(2).shopPrice);
                helper.addOnClickListener(R.id.goods_img3);
            }
        }

        setOnItemChildClickListener(this);

    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        switch (view.getId())
        {
            case R.id.goods_img1:
                CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).goods.get(0).spu);
                break;
            case R.id.goods_img2:
                CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).goods.get(1).spu);
                break;
            case R.id.goods_img3:
                CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).goods.get(2).spu);
                break;
        }
        return false;
    }
}
