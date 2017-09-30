package com.xzry.takeshow.ui.order;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerConfirmOrderActivityComponent;
import com.xzry.takeshow.dagger.module.ConfirmOrderPresenterModule;
import com.xzry.takeshow.entity.AddressEntity;
import com.xzry.takeshow.entity.ConsigneeInfo;
import com.xzry.takeshow.entity.PaymentEntity;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.commodity.StoreInfo;
import com.xzry.takeshow.entity.request.OrderRequest;
import com.xzry.takeshow.ui.login.LoginActivity;
import com.xzry.takeshow.ui.mine.AddressActivity;
import com.xzry.takeshow.ui.order.adapter.ConfirmOrderAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static android.R.id.list;

/**
 * Created by 周东阳 on 2017/8/26 0026.
 */

public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, ConfirmOrderContract.View, ChoosePaymentWayFragment.ClickLisenter {

    @BindView(R.id.confirm_order_title)
    TitleBarView mTitle;
    @BindView(R.id.order_recyclerView)
    RecyclerView order_recyclerView;
    @BindView(R.id.total_money)
    TextView total_money;
    @BindView(R.id.payment)
    TextView payment;

    public static int CHOOSE_ADDRESS = 100;

    private int position;
    private ArrayList<StoreInfo> storeList;
    private ConfirmOrderAdapter mAdapter;

    private TextView address_hint;
    private TextView name;
    private TextView mobile;
    private TextView address;

    private ChoosePaymentWayFragment pDialogFragment;

    private IWXAPI api;

    @Inject
    ConfirmOrderPresenter presenter;

    public static void intentConfirmOrderActivity(Context activity, Goods goods, String size) {
        Intent intent = new Intent(activity, ConfirmOrderActivity.class);
        intent.putExtra("goods", goods);
        intent.putExtra("size", size);
        activity.startActivity(intent);
    }

    public static void intentConfirmOrderActivity(Context activity, ArrayList<StoreInfo> storeInfos, String size) {
        Intent intent = new Intent(activity, ConfirmOrderActivity.class);
        intent.putExtra("size", size);
        intent.putExtra("storeInfos", storeInfos);
        activity.startActivity(intent);
    }

    public static void returnActivity(Context activity) {
        Intent intent = new Intent(activity, ConfirmOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initView() {

        DaggerConfirmOrderActivityComponent.builder()
                .appComponent(getAppComponent())
                .confirmOrderPresenterModule(new ConfirmOrderPresenterModule(this))
                .build()
                .inject(this);

        mTitle.setTitle("确认订单");
        mTitle.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        storeList = new ArrayList<>();
        order_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        order_recyclerView.addItemDecoration(new SpaceItemLine(ScreenUtil.dip2px(this,10)));
        mAdapter = new ConfirmOrderAdapter();
        mAdapter.setOnItemChildClickListener(this);
        order_recyclerView.setAdapter(mAdapter);

        EventBus.getDefault().register(this);

        api = WXAPIFactory.createWXAPI(this, "wxaa803e487961ae06");

    }

    @Override
    protected void initData() {
        if (TextUtils.equals(getIntent().getStringExtra("size"), "1")){
            Goods goods = (Goods) getIntent().getSerializableExtra("goods");
            StoreInfo storeInfo = new StoreInfo();
            storeInfo.storeID = goods.storeInfo.storeID;
            storeInfo.storePhoneNum = goods.storeInfo.storePhoneNum;
            storeInfo.storeName = goods.storeInfo.storeName;
            storeInfo.address = goods.storeInfo.storePhoneNum;
            storeInfo.latitude = goods.storeInfo.storePhoneNum;
            storeInfo.longitude = goods.storeInfo.storePhoneNum;
            storeInfo.storeImg = goods.storeInfo.storePhoneNum;
            ArrayList<Goods> goodsList = new ArrayList<>();
            goodsList.add(goods);
            storeInfo.shoppingCarGoods = goodsList;

            storeList.add(storeInfo);
        }else {
            storeList = (ArrayList<StoreInfo>) getIntent().getSerializableExtra("storeInfos");
        }

        mAdapter.addHeaderView(addTopView());
        mAdapter.setNewData(storeList);


        float money = (float) 0.00;
        for (StoreInfo storeInfo : storeList){
            for (Goods goods : storeInfo.shoppingCarGoods){
                money = money + (Float.parseFloat(goods.shopPrice) * goods.itemNum);
            }
        }
        total_money.setText("￥" + money);
    }

    @Override
    protected void initListener() {
        payment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.payment:
                if (BaseValue.ISLOGIN)
                {
                    pDialogFragment = new ChoosePaymentWayFragment(ConfirmOrderActivity.this, "", this);
                    pDialogFragment.show(getFragmentManager(), "pDialogFragment");
                }else {
                    LoginActivity.startActivity(this);
                }
                break;
        }
    }

    @Subscribe
    public void onEvent(ConsigneeInfo consigneeInfo){
        storeList.get(position).logisticsType = 1;
        storeList.get(position).consigneeMobile = consigneeInfo.mobile;
        storeList.get(position).consigneeName = consigneeInfo.name;
        storeList.get(position).time = consigneeInfo.time;
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onEvent(String expressage){
        storeList.get(position).logisticsType = 0;
        storeList.get(position).consigneeMobile = "";
        storeList.get(position).consigneeName = "";
        storeList.get(position).time = "";
        mAdapter.notifyDataSetChanged();

    }

    public View addTopView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View carwaitView = inflater.inflate(R.layout.view_confirmorder_top, null);
        carwaitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressActivity.initActivityForResult(ConfirmOrderActivity.this, CHOOSE_ADDRESS);
            }
        });

        name = (TextView) carwaitView.findViewById(R.id.name);
        mobile = (TextView) carwaitView.findViewById(R.id.mobile);
        address = (TextView) carwaitView.findViewById(R.id.address);

        address_hint = (TextView) carwaitView.findViewById(R.id.address_hint);
        return carwaitView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_ADDRESS){
            AddressEntity addressEntity = (AddressEntity) data.getSerializableExtra("address");
            name.setText(addressEntity.consignee);
            mobile.setText(addressEntity.mobile);
            address.setText(addressEntity.address);

            address_hint.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件接受
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        this.position = position;
        DistributionWayActivity.initActivity(ConfirmOrderActivity.this, storeList.get(position));
        return false;
    }

    @Override
    public void onPayClickListener(String payment) {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setActualAmount("488");
        orderRequest.setAddress(address.getText().toString());
        orderRequest.setBuyNow("0");
        orderRequest.setCity("沈阳市");
        orderRequest.setCityId("1");
        orderRequest.setConsignee(name.getText().toString());
        orderRequest.setDistrict("市辖区");
        orderRequest.setDistrictId("1");
        orderRequest.setIntegral("0");
        orderRequest.setMobile(mobile.getText().toString());
        orderRequest.setOrderDiscount("0");
        orderRequest.setOrderNote("");
        orderRequest.setPaymentType("微信支付");
        orderRequest.setOrderTotalAmount("488");
        orderRequest.setPaymentTypeId("1");
        orderRequest.setProvince("辽宁省");
        orderRequest.setProvinceId("1");
        orderRequest.setZipcode("");
        orderRequest.setTel("");
        orderRequest.setItems(getAllGoods());
        orderRequest.setStake(getAllStake());

        Log.i("orderRequest", orderRequest.toString());
        pDialogFragment.dismiss();
        Map<String, String> map = new HashMap<>();
        map.put("token", BaseValue.TOKEN);
        map.put("message", orderRequest.toString());
        presenter.submitOrder(map);

    }


    private List<OrderRequest.Item> getAllGoods(){
        List<OrderRequest.Item> items = new ArrayList<>();
        for (StoreInfo storeInfo : storeList){
            for (Goods goods : storeInfo.shoppingCarGoods){
                OrderRequest.Item item = new OrderRequest.Item();
                item.setItemName(goods.goodsName);
                item.setCouponId("");
                item.setItemColor(goods.color);
                item.setItemDiscount("0");
                item.setItemDiscountRate("0");
                item.setItemModel("");
                item.setItemNum(goods.itemNum+"");
                item.setItemPrice(goods.shopPrice);
                item.setItemPriceNow(goods.shopPrice);
                item.setSku(goods.sku);
                item.setStoreSku(goods.storeSku);
                item.setStoreId(storeInfo.storeID);
                item.setSpu(goods.spu);
                item.setOrderDiscountTypeId("1");
                item.setOrderDiscountType("微信支付");
                item.setItemSize(goods.size);

                items.add(item);
            }
        }
        return items;
    }


    private List<OrderRequest.Stake> getAllStake(){
        List<OrderRequest.Stake> stakes = new ArrayList<>();
        for (StoreInfo storeInfo : storeList){
            OrderRequest.Stake stake = new OrderRequest.Stake();
            stake.setStoreId(storeInfo.storeID);
            stake.setAppointmentTime(storeInfo.time);
            stake.setLogisticsType(storeInfo.logisticsType + "");
            stake.setTakeMobile(storeInfo.consigneeMobile);
            stake.setTakeUser(storeInfo.consigneeName);
            stakes.add(stake);

            stakes.add(stake);
        }
        return stakes;
    }


    @Override
    public void payment(PaymentEntity paymentEntity) {
        PayReq req = new PayReq();
        req.appId = paymentEntity.appid;
        req.partnerId = paymentEntity.partnerid;
        req.prepayId = paymentEntity.prepayid;
        req.nonceStr = paymentEntity.noncestr;
        req.timeStamp = paymentEntity.timestamp;
        req.packageValue = "Sign=WXPay";
        req.sign = paymentEntity.sign;
        api.sendReq(req);

//        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
//            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialoginterface, int i) {
//                            //
//                            finish();
//                        }
//                    }).show();
//            return;
//        }
//
//        /**
//         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
//         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
//         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
//         *
//         * orderInfo的获取必须来自服务端；
//         */
//        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//
//        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
//        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
//        final String orderInfo = orderParam + "&" + sign;
//
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(PayDemoActivity.this);
//                Map<String, String> result = alipay.payV2(orderInfo, true);
//                Log.i("msp", result.toString());
//
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();

    }
}
