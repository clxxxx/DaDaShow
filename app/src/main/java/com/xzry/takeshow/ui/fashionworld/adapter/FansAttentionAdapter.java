package com.xzry.takeshow.ui.fashionworld.adapter;


import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/24 0024.
 */

public class FansAttentionAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

    public FansAttentionAdapter(List<String> data){
        super(R.layout.item_fans_attention, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
    }

}