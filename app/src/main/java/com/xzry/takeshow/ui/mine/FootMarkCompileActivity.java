package com.xzry.takeshow.ui.mine;

import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

public class FootMarkCompileActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.foot_mark_recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayout() {
        return R.layout.activity_foot_mark_compile;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
