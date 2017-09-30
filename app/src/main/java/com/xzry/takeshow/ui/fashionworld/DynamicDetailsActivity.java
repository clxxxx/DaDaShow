package com.xzry.takeshow.ui.fashionworld;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.commodityInfo.adapter.ImgsAdapter;
import com.xzry.takeshow.ui.fashionworld.adapter.DynamicDiscussAdpter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/21 0021.
 */

public class DynamicDetailsActivity extends BaseActivity{

    @BindView(R.id.dynamic_detail_title)
    TitleBarView mtitle;
    @BindView(R.id.dynamic_img)
    ListView dynamic_img;

    @BindView(R.id.dynamic_discuss)
    RecyclerView dynamic_discuss;

    public static void intentDynamicDetailsActivity(Context activity) {
        Intent intent = new Intent(activity, DynamicDetailsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_dynamic_details;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("动态详情");
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

        List<String> data2 = new ArrayList<>();
        data2.add("品名");
        data2.add("颜色");
        data2.add("货号");
        data2.add("面料");
        data2.add("面料");
        data2.add("面料");
        ImgsAdapter adapter2 = new ImgsAdapter(this, data2);
        dynamic_img.setAdapter(adapter2);

        dynamic_discuss.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        List<String> data = new ArrayList<>();
        data.add("品名");
        data.add("品名");
        data.add("品名");
        DynamicDiscussAdpter adapter = new DynamicDiscussAdpter(data);
        dynamic_discuss.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
