package com.xzry.takeshow.ui.shoppingcart.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.commodity.StoreInfo;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/19 0019.
 */

public class ShoppingCarAdapter extends BaseQuickAdapter<StoreInfo, BaseViewHolder> {

    private OnItemChildClickListener storeClickListener;
    private OnItemChildClickListener goodsClickListener;
    private OnItemLongClickListener goodsLongClickListener;

    private boolean isEdit;

    public ShoppingCarAdapter(List<StoreInfo> data) {
        super(R.layout.item_shoppingcar_headview, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreInfo item, int position) {
        helper.addOnClickListener(R.id.selected_store);
        helper.getView(R.id.selected_store).setSelected(true);
        for (Goods goods:item.shoppingCarGoods){
            if (!goods.selected)
                helper.getView(R.id.selected_store).setSelected(false);
        }

        ((TextView) helper.getView(R.id.store_name)).setText(item.storeName);

        RecyclerView goods = helper.getView(R.id.goods_recyclerView);
        goods.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        EffectiveGoodsAdapter adapter = new EffectiveGoodsAdapter(R.layout.item_shoppingcar, item.shoppingCarGoods);
        adapter.setClickListener(goodsClickListener);
        adapter.setOnItemLongClickListener(goodsLongClickListener);
        adapter.setEditMode(isEdit);
        goods.setAdapter(adapter);

        setOnItemChildClickListener(storeClickListener);

    }

    public void setStoreClickListener(OnItemChildClickListener storeClickListener){
        this.storeClickListener = storeClickListener;
    }

    public void setGoodsClickListener(OnItemChildClickListener goodsClickListener){
        this.goodsClickListener = goodsClickListener;
    }

    public void setGoodsLongClickListener(OnItemLongClickListener goodsLongClickListener){
        this.goodsLongClickListener = goodsLongClickListener;
    }

    public void setEditMode(boolean isEdit){
        this.isEdit = isEdit;
    }
}
