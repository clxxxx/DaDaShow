package com.xzry.takeshow.ui.integralshop.adapter;

import android.view.View;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.homeEntity.IntegralEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-15
 * @description:
 */


public class IntegralFragmentAdapter extends BaseQuickAdapter<IntegralEntity.item,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener{
    public IntegralFragmentAdapter(List<IntegralEntity.item> list){
        super(R.layout.item_integral_shop_recyclerview,list);
    }
    @Override
    protected void convert(BaseViewHolder helper, IntegralEntity.item item, int position) {
        ((ExpandImageView)helper.getView(R.id.iv_show_img)).setImageURI(item.imageUrl);
        helper.setText(R.id.tv_stock,item.stock+"");
        helper.setText(R.id.tv_title,item.subName);
        helper.setText(R.id.tv_price_old,String.valueOf(item.marketPrice));
        helper.setText(R.id.tv_integral,item.point+"");
        helper.setText(R.id.tv_price_new,String.valueOf(item.shopPrice));
        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
