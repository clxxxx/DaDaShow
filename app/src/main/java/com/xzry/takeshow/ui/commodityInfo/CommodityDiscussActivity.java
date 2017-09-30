package com.xzry.takeshow.ui.commodityInfo;

import android.app.Activity;
import android.content.Intent;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/14 0014.
 */

public class CommodityDiscussActivity extends BaseActivity {

    @BindView(R.id.commodity_discuss_title)
    TitleBarView mtitle;


    public static void intentCommodityDiscuss(Activity activity) {
        Intent intent = new Intent(activity, CommodityDiscussActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_commodity_discuss;
    }

    @Override
    protected void initView() {
        mtitle.setTitle("商品评价");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
