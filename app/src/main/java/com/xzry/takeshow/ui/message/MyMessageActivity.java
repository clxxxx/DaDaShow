package com.xzry.takeshow.ui.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.message.adapter.MessageAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/19 0019.
 */

public class MyMessageActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.my_message_title)
    TitleBarView mtitle;
    @BindView(R.id.message_recyclerView)
    RecyclerView mRecyclerView;


    public static void intentMyMessageActivity(Context activity) {
        Intent intent = new Intent(activity, MyMessageActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("我的消息");
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
        MessageAdapter adapter = new MessageAdapter(data);
        adapter.addHeaderView(addHeadView());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.logistics_inform:
                CheckLogisticsActivity.intentCheckLogisticsActivity(this);
                break;
        }
    }

    //add消息head view
    public View addHeadView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        View headview = inflater.inflate(R.layout.view_message_head, null);
        LinearLayout logistics_inform = (LinearLayout) headview.findViewById(R.id.logistics_inform);
        logistics_inform.setOnClickListener(this);
        return headview;
    }
}
