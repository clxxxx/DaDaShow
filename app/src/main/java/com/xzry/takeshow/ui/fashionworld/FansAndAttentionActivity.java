package com.xzry.takeshow.ui.fashionworld;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.fashionworld.adapter.FansAttentionAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/24 0024.
 */

public class FansAndAttentionActivity extends BaseActivity {

    @BindView(R.id.fans_attention_title)
    TitleBarView mtitle;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static void intentFansAndAttentionActivity(Context activity) {
        Intent intent = new Intent(activity, FansAndAttentionActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_fans_attention;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("他的粉丝");
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
        FansAttentionAdapter adapter = new FansAttentionAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
