package com.xzry.takeshow.ui.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class PickCodeActivity extends BaseActivity {

    @BindView(R.id.pick_code_title)
    TitleBarView mtitle;

    public static void intentPickCodeActivity(Context activity) {
        Intent intent = new Intent(activity, PickCodeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_pick_code;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("提货码");
        mtitle.setTitlebarListener(new TitleBarView.BtnClickListener() {
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

    }
}
