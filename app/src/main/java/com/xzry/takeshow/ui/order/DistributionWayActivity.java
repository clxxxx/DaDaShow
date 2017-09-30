package com.xzry.takeshow.ui.order;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.commodity.StoreInfo;
import com.xzry.takeshow.widget.TitleBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/28 0028.
 */

public class DistributionWayActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.distribution_way_title)
    TitleBarView mTitle;
    @BindView(R.id.expressage)
    LinearLayout expressage;
    @BindView(R.id.getStore)
    LinearLayout getStore;


    public static void initActivity(Context activity, StoreInfo storeInfo) {
        Intent intent = new Intent(activity, DistributionWayActivity.class);
        intent.putExtra("storeInfo", storeInfo);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_distribution_way;
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
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        getStore.setOnClickListener(this);
        expressage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.expressage:
                EventBus.getDefault().post("expressage");
                finish();
                break;
            case R.id.getStore:
                PickupStoreInfoActivity.initActivity(this, (StoreInfo) getIntent().getSerializableExtra("storeInfo"));
                break;
        }
    }

}
