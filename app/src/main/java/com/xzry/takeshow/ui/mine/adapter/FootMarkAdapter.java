package com.xzry.takeshow.ui.mine.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.FootMarkEntity;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */


public class FootMarkAdapter extends BaseQuickAdapter<FootMarkEntity.Item,BaseViewHolder> {
    List<FootMarkEntity.Item> list;
    public FootMarkAdapter(List<FootMarkEntity.Item> list){
        super(R.layout.item_collect_goods,list);
        this.list = list;
    }
    @Override
    protected void convert(BaseViewHolder helper, final FootMarkEntity.Item item, int position) {
        ((ExpandImageView)helper.getView(R.id.goods_img)).setImageURI(item.imageUrl);
        helper.setText(R.id.goods_title,item.goodsName);
        helper.setText(R.id.goods_new_price,item.shopPrice);
        helper.setText(R.id.goods_old_price,item.marketPrice);
        CheckBox checkBox = helper.getView(R.id.goods_check);
        if (item.showStatus) {
            checkBox.setVisibility(View.VISIBLE);
        }else if (item.showStatus) {
            checkBox.setVisibility(View.GONE);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    item.selectStatus = true;
                }else {
                    item.selectStatus = false;
                }
            }
        });
    }
    public List getList(){
        return list;
    };
}
