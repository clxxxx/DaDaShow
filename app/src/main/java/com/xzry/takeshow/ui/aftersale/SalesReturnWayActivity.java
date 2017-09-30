package com.xzry.takeshow.ui.aftersale;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/18 0018.
 */

public class SalesReturnWayActivity extends BaseActivity {

    @BindView(R.id.add_logistics_info_title)
    TitleBarView mtitle;
    @BindView(R.id.sales_return_info)
    FrameLayout sales_return_info;
    @BindView(R.id.sales_return_way)
    RadioGroup sales_return_way;
    @BindView(R.id.espress)
    RadioButton espress;
    @BindView(R.id.store)
    RadioButton store;

    private FragmentManager mFragmentManager;
    private SalesReturnStoreFragment salesReturnStoreFragment;
    private SalesReturnExpressFragment salesReturnExpressFragment;

    public static void intentSalesReturnWayActivity(Context activity) {
        Intent intent = new Intent(activity, SalesReturnWayActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_add_logistics_info;
    }

    @Override
    protected void initView() {
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

        mFragmentManager = getSupportFragmentManager();
        salesReturnStoreFragment = (SalesReturnStoreFragment) mFragmentManager.findFragmentByTag("salesReturnStoreFragment");
        salesReturnExpressFragment = (SalesReturnExpressFragment) mFragmentManager.findFragmentByTag("salesReturnExpressFragment");
        if(salesReturnStoreFragment == null){
            salesReturnStoreFragment = SalesReturnStoreFragment.newInstance();
            addFragment(R.id.sales_return_info, salesReturnStoreFragment, "salesReturnStoreFragment");
        }
        if(salesReturnExpressFragment == null){
            salesReturnExpressFragment = SalesReturnExpressFragment.newInstance();
            addFragment(R.id.sales_return_info, salesReturnExpressFragment, "salesReturnExpressFragment");
        }
        mFragmentManager.beginTransaction().show(salesReturnStoreFragment).hide(salesReturnExpressFragment).commitAllowingStateLoss();



    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        sales_return_way.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.store){
                    mFragmentManager.beginTransaction().show(salesReturnStoreFragment).hide(salesReturnExpressFragment).commitAllowingStateLoss();
                }else {
                    mFragmentManager.beginTransaction().show(salesReturnExpressFragment).hide(salesReturnStoreFragment).commitAllowingStateLoss();
                }

            }
        });
    }
}
