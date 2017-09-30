package com.xzry.takeshow.ui.order;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.ConsigneeInfo;
import com.xzry.takeshow.entity.commodity.StoreInfo;
import com.xzry.takeshow.widget.TitleBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/9/12 0012.
 */

public class PickupStoreInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.pickinfo_title)
    TitleBarView mTitle;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.choose_time)
    LinearLayout choose_time;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.choose_store)
    TextView choose_store;
    @BindView(R.id.store_info)
    LinearLayout store_info;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.store_address)
    TextView store_address;
    @BindView(R.id.business_hours)
    TextView business_hours;
    @BindView(R.id.appointment)
    TextView appointment;

    @BindView(R.id.store_name)
    TextView store_name;

    private StoreInfo storeInfo;

    public static void initActivity(Context activity, StoreInfo storeInfo) {
        Intent intent = new Intent(activity, PickupStoreInfoActivity.class);
        intent.putExtra("storeInfo", storeInfo);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_pickupstore_info;
    }

    @Override
    protected void initView() {
        mTitle.setTitle("配送方式");
        mTitle.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        storeInfo = (StoreInfo) getIntent().getSerializableExtra("storeInfo");
        if (storeInfo == null)
            Log.i("asdasd","qweqwe");
        if (!TextUtils.isEmpty(storeInfo.storeName))
            store_name.setText(storeInfo.storeName);
//        distance.setText("距收货地址：" + );
        if (!TextUtils.isEmpty(storeInfo.address))
            store_address.setText("地址：" + storeInfo.address);

    }

    @Subscribe
    public void onEvent(String message){
        time.setText(message);
    }

    @Override
    protected void initListener() {
        choose_time.setOnClickListener(this);
        choose_store.setOnClickListener(this);
        appointment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_time:
                ChooseTimeFragment pDialogFragment = new ChooseTimeFragment(this);
                pDialogFragment.show(getFragmentManager(), "pDialogFragment");
                break;
            case R.id.choose_store:

                break;
            case R.id.appointment:
                ConsigneeInfo consigneeInfo = new ConsigneeInfo();
                consigneeInfo.mobile = mobile.getText().toString().trim();
                consigneeInfo.name = name.getText().toString().trim();
                consigneeInfo.time = time.getText().toString().trim();
                EventBus.getDefault().post(consigneeInfo);
                ConfirmOrderActivity.returnActivity(this);
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件接受
        EventBus.getDefault().unregister(this);
    }
}
