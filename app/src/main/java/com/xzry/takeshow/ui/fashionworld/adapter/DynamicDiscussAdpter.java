package com.xzry.takeshow.ui.fashionworld.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/23 0023.
 */

public class DynamicDiscussAdpter extends BaseQuickAdapter<String, BaseViewHolder>{

    public DynamicDiscussAdpter(List<String> data){
        super(R.layout.item_dynamic_discuss, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {

    }
}
