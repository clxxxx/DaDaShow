package com.xzry.takeshow.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/** 主页fragment  适配器
 * Created by Administrator on 2016-10-27.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    FragmentManager fm;
    public MainFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fm=fm;
        this.list=list;
    }
    public void updateList(List<Fragment> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
