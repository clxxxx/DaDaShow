package com.xzry.takeshow.ui.aftersale.adapter;

import android.view.View;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.aftersale.AfterSaleStateActivity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class AfterSaleOrderAdapter extends BaseQuickAdapter<String, BaseViewHolder>
    implements BaseQuickAdapter.OnItemClickListener{

    public AfterSaleOrderAdapter(List<String> data){
        super(R.layout.item_after_sale_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AfterSaleStateActivity.intentAfterSaleStateActivity(mContext);
    }
}