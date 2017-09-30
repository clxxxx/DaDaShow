package com.xzry.takeshow.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/16 0016.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    FragmentManager fm;
    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
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
