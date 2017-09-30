package com.xzry.takeshow.ui.fashionworld;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseFragment;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.main.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/3 0003.
 */

public class FashionWorldFragment extends BaseFragment implements ViewPager.OnPageChangeListener,View.OnClickListener{

    @BindView(R.id.fashion_tab)
    RelativeLayout fashion_tab;
    @BindView(R.id.fashion_recommend)
    TextView fashion_recommend;
    @BindView(R.id.fashion_attention)
    TextView fashion_attention;
    @BindView(R.id.tab_iv)
    ImageView tab_iv;
    @BindView(R.id.camera)
    ImageView camera;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private List<Fragment> fragmentList = new ArrayList<>();
    //需要切换的布局
    FashionRecommendFragment recommendFragment;
    FashionAttentionFragment attentionFragment;
    FragmentAdapter fragmentAdapter;
    /**
     * tab的宽度
     */
    private int tabWidth;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;


    public static FashionWorldFragment newInstance() {
        return new FashionWorldFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_fashion_world;
    }

    @Override
    protected void initView() {
        fragmentList.add(recommendFragment = FashionRecommendFragment.newInstance());
        fragmentList.add(attentionFragment = FashionAttentionFragment.newInstance());
        fragmentAdapter = new FragmentAdapter(getFragmentManager(),fragmentList);
        viewpager.setAdapter(fragmentAdapter);

        //设置titlebar下划线的长度
        ViewTreeObserver viewTreeObserver = fashion_tab.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                tabWidth = fashion_tab.getWidth();
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tab_iv.getLayoutParams();
                lp.width = tabWidth /2 ;
                tab_iv.setLayoutParams(lp);
                return true;
            }
        });
        fashion_recommend.setSelected(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        camera.setOnClickListener(this);
        fashion_recommend.setOnClickListener(this);
        fashion_attention.setOnClickListener(this);
        viewpager.setOnPageChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fashion_recommend:
                viewpager.setCurrentItem(0);
                break;
            case R.id.fashion_attention:
                viewpager.setCurrentItem(2);
                break;
            case R.id.camera:
                PublishDyanmicActivity.intentPublishDyanmicActivity(getActivity());
//                TakeCameraDialog takeCameraDialog = new TakeCameraDialog();
//                takeCameraDialog.show(getActivity().getFragmentManager(), "takeCameraDialog");
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
                fashion_recommend.setSelected(true);
                fashion_attention.setSelected(false);
                break;
            case 1:
                fashion_recommend.setSelected(false);
                fashion_attention.setSelected(true);
                break;
        }
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
