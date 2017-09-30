package com.xzry.takeshow.ui.homepager;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.StatusBarUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.dagger.component.DaggerBrandShopComponent;
import com.xzry.takeshow.dagger.module.BrandShopModule;
import com.xzry.takeshow.entity.BrandShopEntity;
import com.xzry.takeshow.ui.homepager.adapter.BrandShopItemAdapter;
import com.xzry.takeshow.ui.homepager.contract.BrandShopContract;
import com.xzry.takeshow.ui.homepager.presenter.BrandShopPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


/**
 *  品牌门店
 */
public class BrandShopActivity extends BaseActivity implements BrandShopContract.View,View.OnClickListener,TabLayout.OnTabSelectedListener{
    String TAG = "brandshop";
    @BindView(R.id.brand_sp_back)
    ImageView iv_back;
    @BindView(R.id.brand_sp_search_lay)
    LinearLayout lay_search;
    @BindView(R.id.brand_sp_more)
    ImageView iv_more;
    @BindView(R.id.brands_tablayout)
    TabLayout tabLayout;

    @BindView(R.id.brand_shop_name)
    TextView tv_storeName;
    @BindView(R.id.brand_shop_address)
    TextView tv_address;
    @BindView(R.id.brand_shop_store_img)
    ExpandImageView eiv_storeImg;
    @BindView(R.id.brand_shop_store)
    ImageView iv_store;
    @BindView(R.id.item_brand_shop_new_recyclerview)
    RecyclerView new_recyclerView;
    @BindView(R.id.item_brand_shop_hot_recyclerview)
    RecyclerView hot_recyclerView;
    @BindView(R.id.item_brand_shop_entire_recyclerview)
    RecyclerView entire_recyclerView;
    @BindView(R.id.brands_scrollview)
    NestedScrollView scrollView;
    @BindView(R.id.item_new)
    LinearLayout ll_new;
    @BindView(R.id.item_hot)
    LinearLayout ll_hot;
    @BindView(R.id.item_entire)
    LinearLayout ll_entire;

    @Inject
    BrandShopPresenter mPresenter;
    private int page = 1;
    private List<Fragment> fragmentList = new ArrayList<>();
    public static void toStartActivity(Context context,String storeID) {
        Intent intent = new Intent(context, BrandShopActivity.class);
        intent.putExtra("storeID",storeID);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_brand_shop;
    }

    @Override
    protected void initView() {
        StatusBarUtil.transparencyBar(this);
        DaggerBrandShopComponent.builder()
                .appComponent(getAppComponent())
                .brandShopModule(new BrandShopModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        String storeID = getIntent().getStringExtra("storeID");
        Map mp = new HashMap();
        mp.put("storeID",storeID);
        mPresenter.getData(mp);

        tabLayout.addTab(tabLayout.newTab().setText("热门"));
        tabLayout.addTab(tabLayout.newTab().setText("推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("全部"));
        setIndicator(tabLayout,40,40);
    }


        @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
        lay_search.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.brand_sp_back:
                finish();
                break;
            case R.id.brand_sp_search_lay:

                break;
            case R.id.brand_sp_more:
                showPopupWindow();
                break;

            default:
                break;
        }
    }
    @Override
    public void resultData(BrandShopEntity entity) {
        Log.i(TAG,"----------"+entity.follow);
        if (entity == null) {
            return;
        }
        tv_storeName.setText(entity.storeInfo.storeName);
        tv_address.setText(entity.storeInfo.address);
        eiv_storeImg.setImageURI(entity.storeInfo.storeImg);

        new_recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        hot_recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        entire_recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        new_recyclerView.setAdapter(new BrandShopItemAdapter(entity.goodsNew));
        hot_recyclerView.setAdapter(new BrandShopItemAdapter(entity.recommend));
        List list = new ArrayList();
        list.addAll(entity.goodsNew);
        list.addAll(entity.recommend);
        list.addAll(entity.goodsNew);
        list.addAll(entity.recommend);
        entire_recyclerView.setAdapter(new BrandShopItemAdapter(list));
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(BrandShopActivity.this).inflate(R.layout.dialog_brand_shop, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout lay1 = (LinearLayout) contentView.findViewById(R.id.dialog_brand_shop_1);
        LinearLayout lay2 = (LinearLayout)contentView.findViewById(R.id.dialog_brand_shop_2);
        LinearLayout lay3 = (LinearLayout)contentView.findViewById(R.id.dialog_brand_shop_3);
        LinearLayout lay4 = (LinearLayout)contentView.findViewById(R.id.dialog_brand_shop_4);
        lay1.setOnClickListener(this);
        lay2.setOnClickListener(this);
        lay3.setOnClickListener(this);
        lay4.setOnClickListener(this);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAsDropDown(iv_more);


    }




    @Override
    public void refreshDisplay() {

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
        switch (tab.getPosition()){
            case 0:
                scrollView.smoothScrollTo(0,ll_new.getTop());
                break;
            case 1:
                scrollView.smoothScrollTo(0,ll_hot.getTop());
                break;
            case 2:
                scrollView.smoothScrollTo(0,ll_entire.getTop());
                break;

            default:
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
