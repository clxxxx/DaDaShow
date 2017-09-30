package com.xzry.takeshow.ui.homepager.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.response.SearchDataResponse;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-7
 * @description:
 */


public class SearchItemAdapter extends BaseQuickAdapter<SearchDataResponse.DataBean,BaseViewHolder> {

    public SearchItemAdapter(List<SearchDataResponse.DataBean> list){
        super(R.layout.item_search_hint,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchDataResponse.DataBean item, int position) {
        helper.setText(R.id.search_item_hint,item.goodsKeywords);
    }


}
