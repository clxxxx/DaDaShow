package com.xzry.takeshow.ui.mine.adapter;

import android.view.View;
import android.widget.AdapterView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.HelpService;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-8-19
 * @description:
 */


public class HelpServiceAdapter extends BaseQuickAdapter<HelpService,BaseViewHolder> {
    public HelpServiceAdapter(List<HelpService> list){
        super(R.layout.item_help_service,list);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HelpService item, int position) {
        helper.setText(R.id.help_service_title,item.getTitle()).setOnItemClickListener(R.id.help_service_title, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                helper.setText(R.id.help_service_content,item.getContent()).setVisible(R.id.help_service_content,true);
            }
        });
    }

}
