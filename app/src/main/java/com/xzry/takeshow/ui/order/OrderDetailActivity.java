package com.xzry.takeshow.ui.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.OrderInfo;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.ui.order.adapter.OrderCommodityAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.order_detail_title)
    TitleBarView mtitle;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.state2)
    TextView state2;
    @BindView(R.id.time_remaining)
    TextView time_remaining;
    @BindView(R.id.state_img)
    ImageView state_img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.store_name)
    TextView store_name;
    @BindView(R.id.commodity_recyclerView)
    RecyclerView commodity_recyclerView;
    @BindView(R.id.orderTotalAmount)
    TextView orderTotalAmount;
    @BindView(R.id.freight)
    TextView freight;
    @BindView(R.id.integral)
    TextView integral;
    @BindView(R.id.commodity_total_amount)
    TextView commodity_total_amount;
    @BindView(R.id.orderDiscount)
    TextView orderDiscount;
    @BindView(R.id.actualAmount)
    TextView actualAmount;
    @BindView(R.id.return_integral)
    TextView return_integral;
    @BindView(R.id.contact_seller)
    LinearLayout contact_seller;
    @BindView(R.id.making_call)
    LinearLayout making_call;
    @BindView(R.id.tradeOrderDate)
    TextView tradeOrderDate;
    @BindView(R.id.orderId)
    TextView orderId;
    @BindView(R.id.consignee_information)
    RelativeLayout consignee_information;
    @BindView(R.id.order_state1)
    TextView order_state1;
    @BindView(R.id.order_state2)
    TextView order_state2;

    private OrderInfo orderInfo;

    public static void intentOrderDetailActivity(Context activity, OrderInfo orderInfo) {
        Intent intent = new Intent(activity, OrderDetailActivity.class);
        intent.putExtra("orderInfo", orderInfo);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("订单状态");
        mtitle.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick()
            {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

    }

    @Override
    protected void initData() {
        orderInfo = (OrderInfo) getIntent().getSerializableExtra("orderInfo");

        switch (orderInfo.state){
            case 1:
                state.setVisibility(View.VISIBLE);
                state.setText("等待买家付款");
                //TODO 剩余付款时间

                state_img.setImageResource(R.mipmap.ico_payment);

                order_state1.setText("取消订单");
                order_state1.setVisibility(View.VISIBLE);
                order_state2.setText("付款");
                order_state2.setVisibility(View.VISIBLE);

                if (orderInfo.logisticsType == 0){
                    consignee_information.setVisibility(View.VISIBLE);
                    name.setText(orderInfo.consignee);
                    mobile.setText(orderInfo.mobile);
                    address.setText(orderInfo.address);
                }
                break;
            case 2:
                state2.setVisibility(View.VISIBLE);
                state2.setText("买家已付款");
                state_img.setImageResource(R.mipmap.ico_paid);

                if (orderInfo.logisticsType == 0){
                    order_state1.setText("提醒发货");
                    order_state1.setVisibility(View.VISIBLE);

                    consignee_information.setVisibility(View.VISIBLE);
                    name.setText(orderInfo.consignee);
                    mobile.setText(orderInfo.mobile);
                    address.setText(orderInfo.address);
                }else {
                    order_state2.setText("提货码");
                    order_state2.setVisibility(View.VISIBLE);
                }

                break;
            case 3:
                state.setVisibility(View.VISIBLE);
                state.setText("卖家已发货");
                //TODO 剩余确认收货时间

                state_img.setImageResource(R.mipmap.ico_car);

                consignee_information.setVisibility(View.VISIBLE);
                name.setText(orderInfo.consignee);
                mobile.setText(orderInfo.mobile);
                address.setText(orderInfo.address);

                order_state1.setText("查看物流");
                order_state1.setVisibility(View.VISIBLE);
                order_state2.setText("确认收货");
                order_state2.setVisibility(View.VISIBLE);
                break;
            case 4:
                state2.setVisibility(View.VISIBLE);
                state2.setText("交易成功");
                state_img.setImageResource(R.mipmap.ico_succeed);

                if (orderInfo.logisticsType == 0){
                    order_state1.setText("评价");
                    order_state1.setVisibility(View.VISIBLE);

                    consignee_information.setVisibility(View.VISIBLE);
                    name.setText(orderInfo.consignee);
                    mobile.setText(orderInfo.mobile);
                    address.setText(orderInfo.address);
                }else {
                    order_state1.setText("提货码");
                    order_state1.setVisibility(View.VISIBLE);
                    order_state2.setText("评价");
                    order_state2.setBackgroundColor(Color.WHITE);
                    order_state2.setVisibility(View.VISIBLE);
                }
                break;
            default:
                state2.setVisibility(View.VISIBLE);
                state2.setText("订单关闭");
                state_img.setImageResource(R.mipmap.ico_close);

                if (orderInfo.logisticsType == 0){
                    order_state1.setText("删除订单");
                    order_state1.setVisibility(View.VISIBLE);

                    consignee_information.setVisibility(View.VISIBLE);
                    name.setText(orderInfo.consignee);
                    mobile.setText(orderInfo.mobile);
                    address.setText(orderInfo.address);
                }else {
                    order_state1.setText("提货码");
                    order_state1.setVisibility(View.VISIBLE);
                    order_state2.setText("评价");
                    order_state2.setBackgroundColor(Color.WHITE);
                    order_state2.setVisibility(View.VISIBLE);
                }
                break;
        }
        float commodityTotalAmount = (float) 0.00;
        for (Goods goods : orderInfo.goodsInfos){
            commodityTotalAmount = commodityTotalAmount + (Float.parseFloat(goods.shopPrice) * goods.itemNum);
        }


        store_name.setText(orderInfo.storeName);
        commodity_recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        OrderCommodityAdapter adapter = new OrderCommodityAdapter(orderInfo.goodsInfos);
        commodity_recyclerView.setAdapter(adapter);

        commodity_total_amount.setText("￥" + commodityTotalAmount);
        freight.setText("￥0.00");
        integral.setText("￥" + orderInfo.integral);
        orderDiscount.setText("￥" + orderInfo.orderDiscount);
        orderTotalAmount.setText("￥" + orderInfo.orderTotalAmount);
        actualAmount.setText("￥" + orderInfo.actualAmount);
        tradeOrderDate.setText(orderInfo.tradeOrderDate);
        orderId.setText(orderInfo.orderId);
    }

    @Override
    protected void initListener() {
        contact_seller.setOnClickListener(this);
        making_call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.contact_seller:
                //TODO 联系卖家
                break;
            case R.id.making_call:
                //TODO 拨打电话
                break;
        }
    }
}
