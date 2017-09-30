package com.xzry.takeshow.ui.homepager.adapter;

import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.CategoryEntity;
import com.xzry.takeshow.ui.homepager.SearchDataActivity;

/**
 * Created by 周东阳 on 2017/8/16 0016.
 */

public class CommodityClassicIconAdapter extends BaseQuickAdapter<CategoryEntity, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener{

    public CommodityClassicIconAdapter() {
        super(R.layout.item_commodity_logo);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryEntity item, int position) {
        ((ExpandImageView) helper.getView(R.id.img)).setImageURI(item.categoryImage);
        ((TextView) helper.getView(R.id.name)).setText(item.categoryName);

        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SearchDataActivity.toActivity(mContext, mData.get(position).categoryKeywords);

    }
}
