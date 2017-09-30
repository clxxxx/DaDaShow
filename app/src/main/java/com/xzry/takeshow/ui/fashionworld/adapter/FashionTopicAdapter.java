package com.xzry.takeshow.ui.fashionworld.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/7 0007.
 */

public class FashionTopicAdapter  extends BaseQuickAdapter<String, BaseViewHolder> {

    public FashionTopicAdapter(List<String> data) {
        super(R.layout.item_topic, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
        ((ExpandImageView) helper.getView(R.id.topic_img)).setImageURI("http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg");
        helper.setText(R.id.topic_title,"#标题#");
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_topic,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (0.322 * ScreenUtil.getScreenWidth(mContext)), LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return view;
    }
}