package com.xzry.takeshow.ui.homepager.adapter;

import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.homeEntity.SpecialsEntity;
import com.xzry.takeshow.ui.commodityInfo.CommodityInfoActivity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/31 0031.
 */

public class SpecialsAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener{

    public SpecialsAdapter(List<Goods> data) {
        super(R.layout.item_daily_specials, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item, int position) {
        if (position == 0)
            helper.getView(R.id.left_interval).setVisibility(View.VISIBLE);

        ((ExpandImageView) helper.getView(R.id.goods_img)).setImageURI(item.imageUrl);
        ((TextView) helper.getView(R.id.goods_name)).setText(item.goodsName);
        ((TextView) helper.getView(R.id.goods_price)).setText("￥" + item.shopPrice);

        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).spu);
    }
}