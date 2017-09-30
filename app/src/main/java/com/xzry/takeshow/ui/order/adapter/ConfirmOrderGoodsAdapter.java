package com.xzry.takeshow.ui.order.adapter;

import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.commodity.Goods;

import java.util.List;

/**
 * Created by 周东阳 on 2017/9/14 0014.
 */

public class ConfirmOrderGoodsAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {

    public ConfirmOrderGoodsAdapter(List<Goods> data){
        super(R.layout.item_confirmorder_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Goods item, int position) {
        ((ExpandImageView) helper.getView(R.id.commodity_img)).setImageURI(item.imageUrl);
        if (TextUtils.isEmpty(item.imageUrl) && item.banner != null)
            ((ExpandImageView) helper.getView(R.id.commodity_img)).setImageURI(item.banner.get(0));

        ((TextView) helper.getView(R.id.commodity_name)).setText(item.goodsName);
        ((TextView) helper.getView(R.id.commodity_color)).setText(item.color);
        ((TextView) helper.getView(R.id.commodity_size)).setText(item.size);
        ((TextView) helper.getView(R.id.current_price)).setText("￥" + item.shopPrice);
        ((TextView) helper.getView(R.id.original_price)).setText("￥" + item.marketPrice);
        ((TextView) helper.getView(R.id.original_price)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        ((TextView) helper.getView(R.id.commodity_count)).setText("x" + item.itemNum);
    }
}
