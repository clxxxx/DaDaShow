package com.xzry.takeshow.ui.homepager.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.response.SearchDataResponse;

import java.util.List;

/** 搜索内容
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */


public class SearchDataAdapter extends BaseQuickAdapter<SearchDataResponse.DataBean,BaseViewHolder> {

    public SearchDataAdapter(List<SearchDataResponse.DataBean> dataset) {
        super(R.layout.item_brand_shop_main,dataset);

    }

    @Override
    protected void convert(BaseViewHolder helper, SearchDataResponse.DataBean item, int position) {
        if (item == null) {
            return;
        }
        ((ExpandImageView)helper.getView(R.id.brand_img)).setImageURI(item.imageUrl);
        helper.setText(R.id.brand_title,item.goodsName);
        helper.setText(R.id.brand_price,item.shopPrice);
    }

}
