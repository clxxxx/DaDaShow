package com.xzry.takeshow.ui.homepager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 周东阳 on 2017/6/7 0007.
 */

public class LocationAdapter extends BaseExpandableListAdapter{

    private Context context;
    private LayoutInflater inflater;

    private List<City> mAllCities;
    //adapter数据
    private List<Map<String, String>> letterList = new ArrayList<Map<String, String>>();
    private List<List<Map<String, String>>> cityList = new ArrayList<List<Map<String, String>>>();


    public LocationAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    public void setCityList(List<Map<String, String>> letterList, List<List<Map<String, String>>> cityList)
    {
        this.letterList = letterList;
        this.cityList = cityList;
        notifyDataSetChanged();
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
        return letterList.size();
    }

    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return cityList.get(groupPosition).size();
    }

    @Override
    public String getGroup(int groupPosition) {

        return letterList.get(groupPosition).get("letter");
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {

        return cityList.get(groupPosition).get(childPosition).get("city");
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {

        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.item_location_letter, null);
            groupHolder.tv_letter = (TextView) convertView.findViewById(R.id.tv_letter);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.tv_letter.setText(getGroup(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.item_location_city, null);
            childHolder.tv_city = (TextView) convertView.findViewById(R.id.tv_city);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.tv_city.setText(getChild(groupPosition, childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        TextView tv_letter;
    }

    class ChildHolder {
        TextView tv_city;
    }
}