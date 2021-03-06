package com.xzry.takeshow.ui.aftersale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.aftersale.adapter.AfterSaleOrderAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class AfterSaleOrderActivity extends BaseActivity {

    @BindView(R.id.after_sale_order_title)
    TitleBarView mtitle;
    @BindView(R.id.after_sale_order_recyclerView)
    RecyclerView mRecyclerView;

    public static void intentAfterSaleOrderActivity(Context activity) {
        Intent intent = new Intent(activity, AfterSaleOrderActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_after_sale_order;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("申请售后");
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        List<String> data = new ArrayList<>();
        data.add("品名");
        data.add("品名");
        data.add("品名");
        data.add("品名");
        data.add("品名");
        AfterSaleOrderAdapter adapter = new AfterSaleOrderAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
