package com.xzry.takeshow.ui.aftersale;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;



/**
 * Created by 周东阳 on 2017/8/18 0018.
 */

public class AfterSaleStateActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.after_sale_state_title)
    TitleBarView mtitle;
    @BindView(R.id.info_view)
    LinearLayout info_view;
    @BindView(R.id.next)
    TextView next;


    public static void intentAfterSaleStateActivity(Context activity) {
        Intent intent = new Intent(activity, AfterSaleStateActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {

        return R.layout.activity_after_sale_state;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("申请售后");
        mtitle.setRightImageResource(R.mipmap.service);
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

        info_view.addView(addStateView());
        info_view.addView(addStoreConsigneeInfoView());

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next:
                SalesReturnWayActivity.intentSalesReturnWayActivity(this);
                break;
        }
    }


    public View addStateView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        View carwaitView = inflater.inflate(R.layout.view_after_sale_state_audit, null);
        return carwaitView;
    }

    public View addStoreConsigneeInfoView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        View carwaitView = inflater.inflate(R.layout.view_store_consignee_info, null);
        return carwaitView;
    }
}
