package com.xzry.takeshow.ui.mine.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.CollectMerchant;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */


public class CollectMerchantAdapter extends BaseQuickAdapter<CollectMerchant.Item,BaseViewHolder> {
    public CollectMerchantAdapter(List<CollectMerchant.Item> list){
        super(R.layout.item_collect_merchant,list);
    }
    @Override
    protected void convert(BaseViewHolder helper, CollectMerchant.Item item, int position) {
        ((ExpandImageView)helper.getView(R.id.store_headimg)).setImageURI(item.storeImg);
        helper.setText(R.id.store_name,item.storeName);
        helper.setText(R.id.store_merchant_count,item.goodsCount);
    }
}
