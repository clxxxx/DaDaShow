package com.xzry.takeshow.ui.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.OrderInfo;
import com.xzry.takeshow.ui.order.adapter.OrderAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/16 0016.
 */

public class WaitReceivingFragment extends BaseFragment {

    @BindView(R.id.order_recyclerView)
    RecyclerView order_recyclerView;

    private OrderAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        order_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mAdapter = new OrderAdapter();
        order_recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public void setData(List<OrderInfo> list, BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener){
        mAdapter.setNewData(list);
        mAdapter.setOnItemChildClickListener(onItemChildClickListener);
    }
}
