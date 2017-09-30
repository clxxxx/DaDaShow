package com.xzry.takeshow.ui.mine.adapter;

import android.widget.Button;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.AddressEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-13
 * @description:
 */


public class AddressShowAdapter extends BaseQuickAdapter<AddressEntity,BaseViewHolder>{
    public AddressShowAdapter(List<AddressEntity> list){
        super(R.layout.item_address_show,list);
    }
    @Override
    protected void convert(BaseViewHolder helper, final AddressEntity item, int position) {
        helper.setText(R.id.item_add_show_name,item.consignee);
        helper.setText(R.id.item_add_show_mobile,item.mobile.substring(0,3)+"****"+item.mobile.substring(7,item.mobile.length()));
        helper.setText(R.id.item_add_show_address,item.province+item.city+item.district+item.address);
        if (item.isDefault == 1) {
            ((Button)helper.getView(R.id.item_add_default)).setSelected(true);
        }
        helper.addOnClickListener(R.id.item_add_default_lay);
        helper.addOnClickListener(R.id.item_add_compile);
        helper.addOnClickListener(R.id.item_add_del);
    }

}
