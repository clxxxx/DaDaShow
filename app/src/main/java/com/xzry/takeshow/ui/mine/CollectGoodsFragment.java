package com.xzry.takeshow.ui.mine;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerCollectComponent;
import com.xzry.takeshow.dagger.module.CollectModule;
import com.xzry.takeshow.entity.CollectGoods;
import com.xzry.takeshow.entity.CollectMerchant;
import com.xzry.takeshow.ui.integralshop.adapter.IntegralDialogAdapter;
import com.xzry.takeshow.ui.mine.adapter.CollectGoodsAdapter;
import com.xzry.takeshow.ui.mine.contract.CollectContract;
import com.xzry.takeshow.ui.mine.presenter.CollectPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


public class CollectGoodsFragment extends BaseFragment implements CollectContract.View,View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    String TAG = "good";
    @Inject
    CollectPresenter mPresenter;
    @BindView(R.id.fg_collect_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.goods_available)
    TextView tv_available;
    @BindView(R.id.goods_promotion)
    TextView tv_promotion;
    @BindView(R.id.goods_type)
    CheckBox ck_goodsType;
    @BindView(R.id.lay_goods_type)
    LinearLayout lay_goodsType;

    private PopupWindow pop;
    private int CK_STATUS = 0;
    private List typeList;

    private int page = 1;
    public static CollectGoodsFragment newInstance(){
        return new CollectGoodsFragment();
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_collect_goods;
    }

    @Override
    protected void initView() {
        DaggerCollectComponent.builder()
                .appComponent(getAppComponent())
                .collectModule(new CollectModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        Map mp = new HashMap();
        mp.put("token", BaseValue.TOKEN);
        mp.put("pageNo",page);
        mPresenter.getGoodsList(mp);

    }

    @Override
    protected void initListener() {
        tv_promotion.setOnClickListener(this);
        tv_available.setOnClickListener(this);
        ck_goodsType.setOnCheckedChangeListener(this);
    }



    @Override
    public void resultGoodsSuccess(CollectGoods goods) {
        if (goods == null) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CollectGoodsAdapter adapter = new CollectGoodsAdapter(goods.goods.rows);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void resultMerchantSuccess(CollectMerchant merchant) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.goods_available://仅看有货

                break;
            case R.id.goods_promotion://正在促销

                break;
            default:
                break;
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            showPOP();
        }
    }
    public void showPOP(){

        typeList = new ArrayList();
        View view = getActivity().getLayoutInflater().inflate(R.layout.item_goods_type, null);

        final int[] position = new int[2];
        recyclerView.getLocationOnScreen(position);

        pop = new PopupWindow(getView(), ViewPager.LayoutParams.MATCH_PARENT,ViewPager.LayoutParams.WRAP_CONTENT);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(getView(), Gravity.TOP, 0, position[1]);
        //弹出窗口关闭事件
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ck_goodsType.setChecked(false);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.goods_type_recyclerview);
        IntegralDialogAdapter adapter = new IntegralDialogAdapter(typeList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String string = (String) typeList.get(position);
                ck_goodsType.setChecked(false);
                pop.dismiss();
            }
        });
    }




}
