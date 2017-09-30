package com.xzry.takeshow.ui.homepager.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.CategoryEntity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/15 0015.
 */

public class TypeOfGoodsNameAdapter  extends BaseQuickAdapter<CategoryEntity,BaseViewHolder> {

    public TypeOfGoodsNameAdapter(List<CategoryEntity> data) {
        super(R.layout.item_goods_type_name, data);
    }

    private int selectedPosition = 0;

    @Override
    protected void convert(BaseViewHolder helper, CategoryEntity item ,int position) {
        helper.setText(R.id.goods_type_name,item.categoryName);
        //恢复默认，防止复用
        helper.getView(R.id.goods_type_name).setSelected(false);
        helper.getView(R.id.goods_type_name).setBackgroundColor(mContext.getResources().getColor(R.color.activity_bg_color));

        if (position == selectedPosition)
        {
            helper.getView(R.id.goods_type_name).setSelected(true);
            helper.getView(R.id.goods_type_name).setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }

    public void setSelectedPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }
}
