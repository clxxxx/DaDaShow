package com.xzry.takeshow.ui.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/19 0019.
 */

public class CheckLogisticsActivity extends BaseActivity {

    @BindView(R.id.check_logistics_title)
    TitleBarView mtitle;
    @BindView(R.id.inform_recyclerView)
    RecyclerView mRecyclerView;


    public static void intentCheckLogisticsActivity(Context activity) {
        Intent intent = new Intent(activity, CheckLogisticsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_check_logistics;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("物流通知");
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> data = new ArrayList<>();
        data.add("品名");
        data.add("品名");
        data.add("品名");
        LogisticsInformAdapter adapter = new LogisticsInformAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
