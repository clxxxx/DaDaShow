package com.xzry.takeshow.ui.message.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/19 0019.
 */

public class MessageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MessageAdapter(List<String> data){
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {

    }
}
