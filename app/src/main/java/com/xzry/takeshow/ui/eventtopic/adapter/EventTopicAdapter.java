package com.xzry.takeshow.ui.eventtopic.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.homeEntity.EventTopicEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-15
 * @description:
 */


public class EventTopicAdapter extends BaseQuickAdapter<EventTopicEntity.item,BaseViewHolder> {
    public EventTopicAdapter(List<EventTopicEntity.item> list){
        super(R.layout.item_brand_shop_main,list);
    }
    @Override
    protected void convert(BaseViewHolder helper, EventTopicEntity.item item, int position) {
        ((ExpandImageView)helper.getView(R.id.brand_img)).setImageURI(item.imageUrl);
        helper.setText(R.id.brand_title,item.subName);
        helper.setText(R.id.brand_price,String.valueOf(item.shopPrice));
    }
}
