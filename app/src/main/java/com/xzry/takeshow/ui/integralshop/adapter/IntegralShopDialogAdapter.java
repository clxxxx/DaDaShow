package com.xzry.takeshow.ui.integralshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xzry.takeshow.R;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-8-19
 * @description:
 */


public class IntegralShopDialogAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;
    public IntegralShopDialogAdapter(Context mContext,List<String> list){
        this.mContext = mContext;
        this.mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (mList.size() > 0){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_integral_shop_popup_top,viewGroup,false);
            viewHolder.tv_text = (TextView) view.findViewById(R.id.item_icon_list);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if(i < mList.size()){
            viewHolder.tv_text.setText(mList.get(i));
//            final ViewHolder finalViewHolder = viewHolder;
//            viewHolder.tv_text.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finalViewHolder.tv_text.setTextColor(R.color.theme_dark_color);
//                }
//            });
        }
        return view;
    }
    private class ViewHolder {
        private TextView tv_text;

    }
}
