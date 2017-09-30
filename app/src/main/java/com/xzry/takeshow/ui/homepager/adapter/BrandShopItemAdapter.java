package com.xzry.takeshow.ui.homepager.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.BrandShopEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-18
 * @description:
 */


public class BrandShopItemAdapter extends BaseQuickAdapter<BrandShopEntity.item,BaseViewHolder> {
    public BrandShopItemAdapter(List<BrandShopEntity.item> list){
        super(R.layout.item_brand_shop_main,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrandShopEntity.item item, int position) {
        ((ExpandImageView)helper.getView(R.id.brand_img)).setImageURI(item.imageUrl);
        helper.setText(R.id.brand_title,item.goodsName);
        helper.setText(R.id.brand_price,String.valueOf(item.shopPrice));
    }

}
