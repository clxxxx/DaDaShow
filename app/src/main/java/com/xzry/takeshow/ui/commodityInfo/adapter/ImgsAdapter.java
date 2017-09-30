package com.xzry.takeshow.ui.commodityInfo.adapter;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;

import java.util.List;

import static com.facebook.drawee.backends.pipeline.Fresco.newDraweeControllerBuilder;

/**
 * Created by 周东阳 on 2017/8/10 0010.
 */

public class ImgsAdapter extends BaseAdapter {

    private Activity mContext;

    private LayoutInflater mInflater;

    private List<String> mDatas;
    ExpandImageView img;

    public ImgsAdapter(Activity context, List<String> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDatas = datas;
    }

    public void changeData(List<String> datas) {
        mDatas = datas;
    }

    public void addData(List<String> datas) {
        if (mDatas != null) {
            mDatas.addAll(datas);
        } else {
            changeData(datas);
        }
    }

    @Override
    public int getCount() {
        return (mDatas != null ? mDatas.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return (mDatas != null ? mDatas.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_image, null);

            holder = new ViewHolder();
            holder.img = (ExpandImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        img = holder.img;

        DraweeController controller = null;
        switch (position)
        {
            case 0:
                controller = newDraweeControllerBuilder()
                        .setUri("http://img30.360buyimg.com/popWaterMark/jfs/t6028/155/7052653619/358316/b94cd9b4/5979bcf8N05184001.jpg")
                        .setControllerListener(listener)
                        .build();
                break;
            case 1:
                controller = newDraweeControllerBuilder()
                        .setUri("http://img30.360buyimg.com/popWaterMark/jfs/t6010/152/7070837609/497809/c1a311c9/5979bcf7N5a5ad8ad.jpg")
                        .setControllerListener(listener)
                        .build();
                break;
            case 2:
                controller = newDraweeControllerBuilder()
                        .setUri("http://img.hb.aicdn.com/25f7cc8f32882079b3f4f4740a5ffb12e9622523b94c-QlhxD6_fw658")
                        .setControllerListener(listener)
                        .build();
                break;
            case 3:
                controller = newDraweeControllerBuilder()
                        .setUri("http://img30.360buyimg.com/popWaterMark/jfs/t6028/155/7052653619/358316/b94cd9b4/5979bcf8N05184001.jpg")
                        .setControllerListener(listener)
                        .build();
                break;
            case 4:
                controller = newDraweeControllerBuilder()
                        .setUri("http://img30.360buyimg.com/popWaterMark/jfs/t6010/152/7070837609/497809/c1a311c9/5979bcf7N5a5ad8ad.jpg")
                        .setControllerListener(listener)
                        .build();
                holder.img.setImageURI(
                        "");
                break;
            case 5:
                controller = newDraweeControllerBuilder()
                        .setUri("http://img30.360buyimg.com/popWaterMark/jfs/t6028/155/7052653619/358316/b94cd9b4/5979bcf8N05184001.jpg")
                        .setControllerListener(listener)
                        .build();
                break;
        }
        holder.img.setController(controller);
        return convertView;
    }

    class ViewHolder {

        ExpandImageView img;
    }


    void updateViewSize(@Nullable ImageInfo imageInfo) {
        if (imageInfo != null) {
            img.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            float imageratio = (float) imageInfo.getWidth() / imageInfo.getHeight();
            img.getLayoutParams().height = (int) ((ScreenUtil.getScreenWidth(mContext)-ScreenUtil.dip2px(mContext, 60))/imageratio);
            notifyDataSetChanged();
        }
    }

    ControllerListener listener = new BaseControllerListener(){

        @Override
        public void onIntermediateImageSet(String id, Object imageInfo) {
            super.onIntermediateImageSet(id, imageInfo);
            updateViewSize((ImageInfo) imageInfo);
        }

        @Override
        public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);
            updateViewSize((ImageInfo) imageInfo);
        }
    };
}