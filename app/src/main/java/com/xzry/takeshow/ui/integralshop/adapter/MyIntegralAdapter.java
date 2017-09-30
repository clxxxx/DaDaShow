package com.xzry.takeshow.ui.integralshop.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.MyIntegralEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-8-26
 * @description:
 */


public class MyIntegralAdapter extends BaseQuickAdapter<MyIntegralEntity.Item,BaseViewHolder> {
    public MyIntegralAdapter(List<MyIntegralEntity.Item> list){
        super(R.layout.item_mine_integral,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyIntegralEntity.Item item, int position) {
        helper.setText(R.id.mi_in_title,item.des);
        helper.setText(R.id.mi_in_integral_up,item.point);
        helper.setText(R.id.mi_in_order_id,item.orderNo);
        helper.setText(R.id.mi_in_data,item.createDate);
    }
}
