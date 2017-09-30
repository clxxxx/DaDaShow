package com.xzry.takeshow.ui.mine;

import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.HelpService;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  客服与帮助
 */
public class HelpServiceActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.help_recyclerView)
    RecyclerView recyclerView;
    List<HelpService> list = new ArrayList<>();
    @Override
    public int getLayout() {
        return R.layout.activity_help_service;
    }

    @Override
    protected void initView() {
       titleBarView.setTitle("客服与帮助");
    }

    @Override
    protected void initData() {
        titleBarView.setTitlebarListener(new TitleBarView.BtnClickListener() {
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
    protected void initListener() {

    }
}
