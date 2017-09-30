package com.xzry.takeshow.ui.aftersale.adapter;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/17 0017.
 */

public class ProblemPhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ProblemPhotoAdapter(List<String> data){
        super(R.layout.item_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {

    }
}
