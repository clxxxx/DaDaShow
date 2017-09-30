package com.xzry.takeshow.ui.commodityInfo.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/10 0010.
 */

public class CommodityParameterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CommodityParameterAdapter(List<String> data){
        super(R.layout.item_commodity_parameter, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {

    }
}
