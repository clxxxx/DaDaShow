package com.xzry.takeshow.ui.mine.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.CollectGoods;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */


public class CollectGoodsAdapter extends BaseQuickAdapter<CollectGoods.Goods.GoodItem,BaseViewHolder>{
    public CollectGoodsAdapter(List<CollectGoods.Goods.GoodItem> list){
        super(R.layout.item_collect_goods,list);
    }


    @Override
    protected void convert(BaseViewHolder helper, CollectGoods.Goods.GoodItem item, int position) {
        ((ExpandImageView)helper.getView(R.id.goods_img)).setImageURI(item.imageUrl);
        helper.setText(R.id.goods_title,item.goodsName);
        helper.setText(R.id.goods_new_price,item.shopPrice);
        helper.setText(R.id.goods_old_price,item.marketPrice);
    }
}
