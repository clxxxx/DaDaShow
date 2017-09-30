package com.xzry.takeshow.ui.fashionworld.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.src;

/**
 * Created by 周东阳 on 2017/8/15 0015.
 */

public class MyGridViewAdapter  extends BaseAdapter {

    private String[] preview_imgUris;
    private Context context;
    private List<String> imgUris = new ArrayList<String>();

    public List<String> getImgUris() {
        return imgUris;
    }

    public MyGridViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.imgUris = data;
    }

    @Override
    public int getCount() {
        return imgUris.size();
    }

    @Override
    public String getItem(int position) {
        return "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            convertView= View.inflate(context, R.layout.item_square_img, null);
            holder = new ViewHolder(convertView);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img.setImageURI(imgUris.get(position));

        return convertView;
    }

    class ViewHolder{
        ExpandImageView img;
        public ViewHolder(View v) {
            img = (ExpandImageView)v.findViewById(R.id.img);
            v.setTag(this);
        }
    }

}