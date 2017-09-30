package com.xzry.takeshow.ui.mine.adapter;

import android.text.TextUtils;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.AttentionEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-20
 * @description:
 */


public class AttentionAdapter extends BaseQuickAdapter<AttentionEntity.Item,BaseViewHolder> {
    public AttentionAdapter(List<AttentionEntity.Item> list){
        super(R.layout.item_attention,list);
    }
    @Override
    protected void convert(BaseViewHolder helper, AttentionEntity.Item item, int position) {
        ((ExpandImageView)helper.getView(R.id.attention_headimg)).setImageURI(item.headerUrl);
        helper.setText(R.id.attention_nickname,item.nickname);
        helper.setText(R.id.attention_dynamic_state,item.dynCount);
        helper.setText(R.id.attention_fans,item.fansCount);
        if (TextUtils.equals(item.followStatus,"1")) {
            helper.setImageResource(R.id.attention_status,R.mipmap.ico_mutual_fans);
        }else if (TextUtils.equals(item.followStatus,"3")) {
            helper.setImageResource(R.id.attention_status,R.mipmap.attention_press);
        }else {
            helper.setImageResource(R.id.attention_status,R.mipmap.attention);
        }
    }
}
