package com.xzry.takeshow.ui.commodityInfo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.RequestEvent;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.ui.main.FragmentAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/9 0009.
 */
@Route(path = "/commodityInfo/CommodityInfoActivity")
public class CommodityInfoActivity extends BaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener,
    CommodityContract.View {

    @BindView(R.id.gaos_viewstub)
    ViewStub gaos_viewstub;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.commodity_tab)
    RelativeLayout commodity_tab;
    @BindView(R.id.commodity_info)
    TextView commodity_info;
    @BindView(R.id.commodity_img)
    TextView commodity_img;
    @BindView(R.id.tab_iv)
    ImageView tab_iv;
    @BindView(R.id.shopping_cart)
    ImageView shopping_cart;
    @BindView(R.id.commodity_share)
    ImageView commodity_share;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    public static final String CHECK_COMMODITY_STOCK = "stock";

    private List<Fragment> fragmentList = new ArrayList<>();
    //需要切换的布局
    CommodityInfoFragment commodityInfoFragment;
    CommodityImageInfoFragment commodityImageInfoFragment;
    FragmentAdapter fragmentAdapter;
    /**
     * tab的宽度
     */
    private int tabWidth;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    private String spu;

    @Inject
    CommodityPresenter mPresenter;

    public static void intentCommodityInfoActivity(Context context, String spu) {
        Intent intent = new Intent(context, CommodityInfoActivity.class);
        intent.putExtra("spu", spu);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_commodityinfo;
    }

    @Override
    protected void initView() {

        DaggerCommodityComponent.builder()
                .appComponent(getAppComponent())
                .commodityPresenterModule(new CommodityPresenterModule(this))
                .build()
                .inject(this);


        fragmentList.add(commodityInfoFragment = CommodityInfoFragment.newInstance());
        fragmentList.add(commodityImageInfoFragment = CommodityImageInfoFragment.newInstance());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragmentList);
        viewpager.setAdapter(fragmentAdapter);

        //设置titlebar下划线的长度
        ViewTreeObserver viewTreeObserver = commodity_tab.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                tabWidth = commodity_tab.getWidth();
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tab_iv
                        .getLayoutParams();
                lp.width = tabWidth /2 ;
                tab_iv.setLayoutParams(lp);
                return true;
            }
        });
        commodity_info.setSelected(true);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        spu = getIntent().getStringExtra("spu");
        Map<String,String> map = new HashMap<>();
        Log.i("latitude", BaseValue.latitude+"");
        Log.i("latitude", BaseValue.longitude+"");
        map.put("latitude", BaseValue.latitude);
        map.put("longitude", BaseValue.longitude);
        map.put("spu",spu);
        mPresenter.getData(map);
    }

    @Override
    protected void initListener() {
        commodity_info.setOnClickListener(this);
        commodity_img.setOnClickListener(this);
        back.setOnClickListener(this);
        viewpager.setOnPageChangeListener(this);
    }

    @Subscribe
    public void onEvent(RequestEvent event){
        switch (event.getType()){
            case RequestEvent.ADDSHOPPINGCAR :
                mPresenter.addShoppingcar(event.getMap(), event.getConsumer());
                break;
            case RequestEvent.CHECKSTOCK :
                mPresenter.checkStock(event.getMap(), event.getConsumer());
                break;
            case RequestEvent.COLLECT :
                mPresenter.collect(event.getMap(), event.getConsumer());
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back:
                finish();
                break;
            case R.id.commodity_info:
                viewpager.setCurrentItem(0);
                break;
            case R.id.commodity_img:
                viewpager.setCurrentItem(1);
                break;
        }
    }

    /**
     * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
     * offsetPixels:当前页面偏移的像素位置
     */
    @Override
    public void onPageScrolled(int position, float offset, int offsetPixels) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tab_iv
                .getLayoutParams();
        /**
         * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
         * 设置mTabLineIv的左边距 滑动场景：
         * 记3个页面,
         * 从左到右分别为0,1,2
         * 0->1;1->0
         */
        if (currentIndex == 0 && position == 0)// 0->1
        {
            lp.leftMargin = (int) (offset * (tabWidth * 1.0 / 2) + currentIndex
                    * (tabWidth / 2));

        } else if (currentIndex == 1 && position == 0) // 1->0
        {
            lp.leftMargin = (int) (-(1 - offset)
                    * (tabWidth * 1.0 / 2) + currentIndex
                    * (tabWidth / 2));

        }
        tab_iv.setLayoutParams(lp);
    }

    @Override
    public void onPageSelected(int position) {
        switch (position)
        {
            case 0:
                commodity_info.setSelected(true);
                commodity_img.setSelected(false);
                break;
            case 1:
                commodity_info.setSelected(false);
                commodity_img.setSelected(true);
                break;
        }
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void setData(Goods goods) {
        commodityInfoFragment.setData(goods);
        commodityImageInfoFragment.setData(goods.desc);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件接受
        EventBus.getDefault().unregister(this);
    }
}
