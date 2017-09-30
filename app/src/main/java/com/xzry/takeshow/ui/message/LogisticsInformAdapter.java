package com.xzry.takeshow.ui.message;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/19 0019.
 */

public class LogisticsInformAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public LogisticsInformAdapter(List<String> data){
        super(R.layout.item_logistics_inform, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {

    }
}
