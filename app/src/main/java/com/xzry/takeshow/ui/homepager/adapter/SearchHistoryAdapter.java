package com.xzry.takeshow.ui.homepager.adapter;

import android.view.View;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.xzry.takeshow.R;

import java.util.List;

/** 搜索历史记录适配器
 * @author: luosy
 * @date: 2017-6-8
 * @description:
 */


public class SearchHistoryAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    private List<String> list;
    public SearchHistoryAdapter(List<String> list){
        super(R.layout.home_search_data_history_style,list);
        this.list = list;
    }
    @Override
    protected void convert(BaseViewHolder helper, final String item, final int position) {
        helper.setText(R.id.search_history_text,item);
        helper.getView(R.id.search_history_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    public void removeAll(){
//        for (int i = 0; i < list.size(); i++) {
//            list.remove(i);
//
//        }
//        notifyItemInserted(i);
    }
}
