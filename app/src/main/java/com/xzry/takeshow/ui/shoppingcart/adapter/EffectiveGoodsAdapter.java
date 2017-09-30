package com.xzry.takeshow.ui.shoppingcart.adapter;

import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseItemDraggableAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;

import java.util.List;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

public class EffectiveGoodsAdapter extends BaseItemDraggableAdapter<Goods, BaseViewHolder> {

    private OnItemChildClickListener onItemChildClickListener;

    private boolean isEdit;

    public EffectiveGoodsAdapter(int layoutId, List<Goods> data){
        super(layoutId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item, int position) {
        helper.addOnClickListener(R.id.choose_goods);
        helper.getView(R.id.choose_goods).setSelected(false);
        if (item.selected)
            helper.getView(R.id.choose_goods).setSelected(true);

        ((ExpandImageView) helper.getView(R.id.goods_img)).setImageURI(item.imageUrl);
        ((TextView) helper.getView(R.id.commodity_name)).setText(item.goodsName);
        ((TextView) helper.getView(R.id.commodity_color)).setText(item.color);
        ((TextView) helper.getView(R.id.commodity_size)).setText(item.size);
        ((TextView) helper.getView(R.id.current_price)).setText("￥" + item.shopPrice);
        ((TextView) helper.getView(R.id.commodity_quantity)).setText("x" + item.itemNum);

        if (isEdit){
            helper.getView(R.id.goods_info).setVisibility(View.GONE);
            helper.getView(R.id.edit_query).setVisibility(View.VISIBLE);
            ((TextView) helper.getView(R.id.compile_quantity)).setText(item.itemNum + "");
        }else {
            helper.getView(R.id.goods_info).setVisibility(View.VISIBLE);
            helper.getView(R.id.edit_query).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.subtract);
        helper.addOnClickListener(R.id.add);
        helper.addOnClickListener(R.id.delete);

        setOnItemChildClickListener(onItemChildClickListener);
    }

    public void setClickListener(OnItemChildClickListener onItemChildClickListener){
        this.onItemChildClickListener = onItemChildClickListener;
    }

    public void setEditMode(boolean isEdit){
        this.isEdit = isEdit;
    }
}
