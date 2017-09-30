package com.xzry.takeshow.ui.mine;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 *  收藏
 */
public class CollectActivity extends BaseActivity implements View.OnClickListener,TabLayout.OnTabSelectedListener{
    @BindView(R.id.collect_back)
    ImageView iv_back;
    @BindView(R.id.collect_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.collect_more)
    ImageView iv_more;
    private FragmentManager fragmentManager;
    private CollectGoodsFragment goodsFragment;
    private CollectMerchantFragment merchantFragment;
    private Fragment[] fragments;
    public static void startActivity(Context context){
        Intent intent = new Intent(context,CollectActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        goodsFragment = (CollectGoodsFragment) fragmentManager.findFragmentByTag("goods_fragment");
        merchantFragment = (CollectMerchantFragment) fragmentManager.findFragmentByTag("merchant_fragment");
        if (goodsFragment == null) {
            goodsFragment = CollectGoodsFragment.newInstance();
            addFragment(R.id.collect_framelayout,goodsFragment,"goods_fragment");
        }
        if (merchantFragment == null) {
            merchantFragment = CollectMerchantFragment.newInstance();
            addFragment(R.id.collect_framelayout,merchantFragment,"merchant_fragment");
        }
        fragments = new Fragment[]{goodsFragment, merchantFragment};
        fragmentManager.beginTransaction().show(goodsFragment).hide(merchantFragment).commitAllowingStateLoss();
    }

    @Override
    protected void initData() {
        tabLayout.addTab(tabLayout.newTab().setText("商品"));
        tabLayout.addTab(tabLayout.newTab().setText("商家"));
        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.collect_back:
                finish();
                break;
            case R.id.collect_more:

                break;

            default:
                break;
        }
    }
    public void setIndicator (TabLayout tabs,int leftDip,int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch(tab.getPosition()){
            case 0:
                fragmentManager.beginTransaction().show(goodsFragment).hide(merchantFragment).commitAllowingStateLoss();
                break;
            case 1:
                fragmentManager.beginTransaction().show(merchantFragment).hide(goodsFragment).commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
