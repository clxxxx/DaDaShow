package com.xzry.takeshow.ui.aftersale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.aftersale.adapter.ProblemPhotoAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class ApplyAfterSaleActivity extends BaseActivity {

    @BindView(R.id.after_sale_title)
    TitleBarView mtitle;
    @BindView(R.id.problem_photo_recyclerView)
    RecyclerView mRecyclerView;

    public static void intentApplyAfterSaleActivity(Context activity) {
        Intent intent = new Intent(activity, ApplyAfterSaleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_apply_aftersale;
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        List<String> data = new ArrayList<>();
        data.add("品名");
        ProblemPhotoAdapter adapter = new ProblemPhotoAdapter(data);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
