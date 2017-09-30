package com.xzry.takeshow.ui.order.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.OrderInfo;
import com.xzry.takeshow.ui.order.OrderDetailActivity;
import com.xzry.takeshow.ui.order.PickCodeActivity;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener{

    public OrderAdapter(){
        super(R.layout.item_order);
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderInfo item, int position) {
        helper.getView(R.id.order_state_goods_code2).setVisibility(View.GONE);
        helper.getView(R.id.order_state_evaluate).setVisibility(View.GONE);
        helper.getView(R.id.order_state_affirm_receive).setVisibility(View.GONE);
        helper.getView(R.id.order_state_check_logistics).setVisibility(View.GONE);
        helper.getView(R.id.order_state_goods_code).setVisibility(View.GONE);
        helper.getView(R.id.order_state_deliver).setVisibility(View.GONE);
        helper.getView(R.id.order_state_cancle).setVisibility(View.GONE);
        helper.getView(R.id.order_state_payment).setVisibility(View.GONE);

        switch (item.state) {
            case 1:
                bindStateDeliverData(helper, item, position);
                break;
            case 2:
                if (item.logisticsType == 0){
                    bindStateObligationData(helper, item, position);
                }else {
                    bindStateObligationZitiData(helper, item, position);
                }
                break;
            case 3:
                bindStateReceivingData(helper, item, position);
                break;
            case 4:
                if (item.logisticsType == 0){
                    bindStateEvaluateData(helper, item, position);
                }else {
                    bindStateEvaluateZitiData(helper, item, position);
                }
                break;
        }

        ((RecyclerView)helper.getView(R.id.commodity_recyclerView)).setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        OrderCommodityAdapter adapter = new OrderCommodityAdapter(item.goodsInfos);
        ((RecyclerView)helper.getView(R.id.commodity_recyclerView)).setAdapter(adapter);
        adapter.setClickListener(this);

        ((TextView) helper.getView(R.id.store_name)).setText(item.storeName);
        ((TextView) helper.getView(R.id.order_state)).setText(item.orderStatus);

    }

    private void bindStateEvaluateZitiData(BaseViewHolder helper, OrderInfo item, int position) {
        helper.getView(R.id.order_state_goods_code2).setVisibility(View.VISIBLE);
        helper.getView(R.id.order_state_evaluate).setVisibility(View.VISIBLE);
    }

    private void bindStateEvaluateData(BaseViewHolder helper, OrderInfo item, int position) {
        helper.getView(R.id.order_state_evaluate).setVisibility(View.VISIBLE);
    }

    private void bindStateReceivingData(BaseViewHolder helper, OrderInfo item, int position) {
        helper.getView(R.id.order_state_affirm_receive).setVisibility(View.VISIBLE);
        helper.getView(R.id.order_state_check_logistics).setVisibility(View.VISIBLE);
    }

    private void bindStateObligationZitiData(BaseViewHolder helper, OrderInfo item, int position) {
        helper.getView(R.id.order_state_goods_code).setVisibility(View.VISIBLE);
        helper.getView(R.id.order_state_goods_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickCodeActivity.intentPickCodeActivity(mContext);
            }
        });
    }

    private void bindStateObligationData(BaseViewHolder helper, OrderInfo item, int position) {
        helper.getView(R.id.order_state_deliver).setVisibility(View.VISIBLE);
    }


    private void bindStateDeliverData(BaseViewHolder helper, OrderInfo item, int position) {
        helper.getView(R.id.order_state_cancle).setVisibility(View.VISIBLE);
        helper.getView(R.id.order_state_payment).setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        OrderDetailActivity.intentOrderDetailActivity(mContext, mData.get(position));
    }
}
