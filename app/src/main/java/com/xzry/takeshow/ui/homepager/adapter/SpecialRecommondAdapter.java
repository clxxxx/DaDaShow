package com.xzry.takeshow.ui.homepager.adapter;

import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.homeEntity.SpecialRecommondEntity;
import com.xzry.takeshow.ui.commodityInfo.CommodityInfoActivity;
import com.xzry.takeshow.ui.eventtopic.EventTopicActivity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/31 0031.
 */

public class SpecialRecommondAdapter extends BaseQuickAdapter<SpecialRecommondEntity, BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener{

    public SpecialRecommondAdapter(List<SpecialRecommondEntity> data) {
        super(R.layout.item_special_recommond, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialRecommondEntity item, int position) {
        ((ExpandImageView) helper.getView(R.id.subject_banner)).setImageURI(item.subjectBanner);

        if (item.goods != null)
        {
            if (item.goods.size() > 0){
                ((ExpandImageView) helper.getView(R.id.goods_img1)).setImageURI(item.goods.get(0).imageUrl);
                ((TextView) helper.getView(R.id.goods_name1)).setText(item.goods.get(0).goodsName);
                ((TextView) helper.getView(R.id.goods_price1)).setText("￥" + item.goods.get(0).shopPrice);
                helper.addOnClickListener(R.id.goods_img1);
            }
            if (item.goods.size() > 1){
                ((ExpandImageView) helper.getView(R.id.goods_img2)).setImageURI(item.goods.get(1).imageUrl);
                ((TextView) helper.getView(R.id.goods_name2)).setText(item.goods.get(1).goodsName);
                ((TextView) helper.getView(R.id.goods_price2)).setText("￥" + item.goods.get(1).shopPrice);
                helper.addOnClickListener(R.id.goods_img2);
            }
            if (item.goods.size() > 2){
                ((ExpandImageView) helper.getView(R.id.goods_img3)).setImageURI(item.goods.get(2).imageUrl);
                ((TextView) helper.getView(R.id.goods_name3)).setText(item.goods.get(2).goodsName);
                ((TextView) helper.getView(R.id.goods_price3)).setText("￥" + item.goods.get(2).shopPrice);
                helper.addOnClickListener(R.id.goods_img3);
            }
            helper.addOnClickListener(R.id.subject_banner);
        }

        setOnItemChildClickListener(this);
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.subject_banner:
                EventTopicActivity.toActivity(mContext,mData.get(position).subjectName, mData.get(position).id);
                break;
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
