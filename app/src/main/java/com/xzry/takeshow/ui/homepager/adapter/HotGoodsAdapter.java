package com.xzry.takeshow.ui.homepager.adapter;

import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.ui.commodityInfo.CommodityInfoActivity;

import java.util.List;


/**
 * Created by 周东阳 on 2017/9/1 0001.
 */

public class HotGoodsAdapter<T extends Goods, K extends BaseViewHolder> extends BaseQuickAdapter<T, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener{

    public HotGoodsAdapter(List<T> data){
        super(R.layout.item_home_hotgoods, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Goods item, int position) {

        ((ExpandImageView) helper.getView(R.id.hotgoods_img)).setImageURI(item.imageUrl);
        ((TextView) helper.getView(R.id.hotgoods_title)).setText(item.goodsName);
        ((TextView) helper.getView(R.id.goods_price)).setText(item.shopPrice);

        setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CommodityInfoActivity.intentCommodityInfoActivity(mContext, mData.get(position).spu);
    }
}
