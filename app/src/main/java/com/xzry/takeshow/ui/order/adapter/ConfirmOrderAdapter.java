package com.xzry.takeshow.ui.order.adapter;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.commodity.StoreInfo;

/**
 * Created by 周东阳 on 2017/9/12 0012.
 */

public class ConfirmOrderAdapter extends BaseQuickAdapter<StoreInfo, BaseViewHolder> {

    public ConfirmOrderAdapter(){
        super(R.layout.item_confirmorder);
    }
    @Override
    protected void convert(BaseViewHolder helper, StoreInfo item, int position) {

        ((TextView) helper.getView(R.id.store_name)).setText(item.storeName);

        RecyclerView goodsRecyclerView = helper.getView(R.id.recyclerView);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        ConfirmOrderGoodsAdapter adapter = new ConfirmOrderGoodsAdapter(item.shoppingCarGoods);
        goodsRecyclerView.setAdapter(adapter);

        boolean isOnline = true;
        float totalprices = (float) 0.00;
        for (Goods goods : item.shoppingCarGoods){
            totalprices = totalprices + ((float) goods.itemNum * Float.parseFloat(goods.shopPrice));
            if (goods.isOnline == 1)
                isOnline = false;
        }

        helper.setText(R.id.goods_total_prices, "￥" + totalprices);

        helper.getView(R.id.distribution_hint).setVisibility(View.GONE);
        if (isOnline)
            helper.getView(R.id.distribution_hint).setVisibility(View.VISIBLE);

        helper.getView(R.id.store_address_info).setVisibility(View.GONE);
        if (item.logisticsType == 1){
            helper.getView(R.id.store_address_info).setVisibility(View.VISIBLE);
            helper.setText(R.id.distribution_type, "门店自提");

            helper.setText(R.id.appointment_time, item.time);
            helper.setText(R.id.appointment_store_name, item.storeName);
            helper.setText(R.id.address, item.address);
            helper.setText(R.id.mobile, item.storePhoneNum);
            helper.setText(R.id.consignee_name, item.consigneeName);
            helper.setText(R.id.consignee_mobile, item.consigneeMobile);
        }

        helper.addOnClickListener(R.id.choose_distribution);


        //        suerintegral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            }
//        });
    }

}
