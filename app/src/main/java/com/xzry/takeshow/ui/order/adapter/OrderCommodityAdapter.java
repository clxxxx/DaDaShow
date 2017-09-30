package com.xzry.takeshow.ui.order.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.ui.order.OrderDetailActivity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class OrderCommodityAdapter extends BaseQuickAdapter<Goods, BaseViewHolder>{

    private OnItemClickListener onItemClickListener;

    public OrderCommodityAdapter(List<Goods> data){
        super(R.layout.item_order_commodity, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Goods item, int position) {

        ((ExpandImageView) helper.getView(R.id.commodity_img)).setImageURI(item.imageUrl);
        ((TextView) helper.getView(R.id.commodity_name)).setText(item.goodsName);
        ((TextView) helper.getView(R.id.commodity_color)).setText(item.color);
        ((TextView) helper.getView(R.id.commodity_size)).setText(item.size);
        ((TextView) helper.getView(R.id.current_price)).setText("￥" + item.shopPrice);
        ((TextView) helper.getView(R.id.original_price)).setText("￥" + item.marketPrice);
        ((TextView) helper.getView(R.id.original_price)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        ((TextView) helper.getView(R.id.commodity_count)).setText("x" + item.itemNum);

        setOnItemClickListener(onItemClickListener);

    }

    public void setClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
