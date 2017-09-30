package com.xzry.takeshow.ui.homepager.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 周东阳 on 2017/9/1 0001.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    List<View> viewList;

    public MyViewPagerAdapter(List<View> lists)
    {
        viewList = lists;
    }


    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return viewList.size();
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(viewList.get(position));
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        ((ViewPager) view).addView(viewList.get(position), 0);
        return viewList.get(position);
    }

//    /**
//     * 页面宽度所占ViewPager测量宽度的权重比例，默认为1
//     */
//    @Override
//    public float getPageWidth(int position) {
//        return (float) 0.8;
//    }
}
