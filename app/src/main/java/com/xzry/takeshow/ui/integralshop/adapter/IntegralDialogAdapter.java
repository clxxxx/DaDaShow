package com.xzry.takeshow.ui.integralshop.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

import static com.xzry.takeshow.R.id.item_icon_list;

/**
 * @author: luosy
 * @date: 2017-9-22
 * @description:
 */


public class IntegralDialogAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public IntegralDialogAdapter(List<String> list){
        super(R.layout.item_integral_shop_popup_top,list);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
        helper.setText(item_icon_list,item);

    }

}
