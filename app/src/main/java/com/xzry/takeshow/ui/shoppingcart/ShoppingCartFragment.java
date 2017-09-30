package com.xzry.takeshow.ui.shoppingcart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.base.baseadapter.callback.ItemDragAndSwipeCallback;
import com.sxjs.common.base.baseadapter.listener.OnItemSwipeListener;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.xzry.takeshow.R;
import com.xzry.takeshow.dagger.component.DaggerShoppingCarFragmentComponent;
import com.xzry.takeshow.dagger.module.ShoppingCarPresenterModule;
import com.xzry.takeshow.entity.ShoppingCar;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.commodity.StoreInfo;
import com.xzry.takeshow.ui.order.ConfirmOrderActivity;
import com.xzry.takeshow.ui.shoppingcart.adapter.ShoppingCarAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener, ShoppingCarContract.View {

    @BindView(R.id.shopping_car)
    TitleBarView mtitle;
    @BindView(R.id.order_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.settle_accounts)
    TextView settle_accounts;

    @BindView(R.id.selected_all)
    LinearLayout selected_all;

    @BindView(R.id.total_money)
    TextView total_money;

    private boolean isEdit = false;
    private List<StoreInfo> list;
    private  ShoppingCarAdapter mAdapter;

    private int count = 0;

    private String skus = "";
    private DialogDelete dialogDelete;

    @Inject
    ShoppingCarPresenter presenter;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView() {

        DaggerShoppingCarFragmentComponent.builder()
                .shoppingCarPresenterModule(new ShoppingCarPresenterModule(this))
                .appComponent(getAppComponent())
                .build()
                .inject(this);

        mtitle.setLeftLayoutVisibility(View.GONE);
        mtitle.setTitle("购物车");
        mtitle.setRightLayoutVisibility(View.VISIBLE);
        mtitle.getRightLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mtitle.setRightLayout2Visibility(View.VISIBLE);
        mtitle.getRightLayout2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit){
                    isEdit = false;
                    mtitle.setRightText2("编辑");
                    calculateMoney();
                }else {
                    isEdit = true;
                    mtitle.setRightText2("完成");
                    settle_accounts.setText("删除");
                }
                mAdapter.setEditMode(isEdit);
                mAdapter.notifyDataSetChanged();
                calculateMoney();
            }
        });
        mtitle.setRightImageResource(R.mipmap.ico_messages);
        mtitle.setRightText2("编辑");

        mAdapter = new ShoppingCarAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new SpaceItemLine(ScreenUtil.dip2px(getContext(),10)));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setStoreClickListener(storeClickListener);
        mAdapter.setGoodsClickListener(goodsClickListener);
        mAdapter.setGoodsLongClickListener(goodsLongClickListener);

        settle_accounts.setText(getString(R.string.shoppingcar_goods_count, 0));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        settle_accounts.setOnClickListener(this);
        selected_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.settle_accounts:
                if (count == 0 )
                    return;
                ArrayList<StoreInfo> storeInfos = new ArrayList<>();
                for (int i=0; i<list.size(); i++){
                    StoreInfo storeInfo = new StoreInfo();
                    ArrayList<Goods> goodses = new ArrayList<>();
                    storeInfo.shoppingCarGoods = new ArrayList<>();
                    for (int j=0; j<list.get(i).shoppingCarGoods.size(); j++){
                        if (list.get(i).shoppingCarGoods.get(j).selected){
                            goodses.add(list.get(i).shoppingCarGoods.get(j));
                            skus = skus + list.get(i).shoppingCarGoods.get(j).sku + ",";
                        }
                    }
                    if (goodses.size() != 0){
                        storeInfo.address = list.get(i).address;
                        storeInfo.latitude = list.get(i).latitude;
                        storeInfo.longitude = list.get(i).longitude;
                        storeInfo.storeID = list.get(i).storeID;
                        storeInfo.storeName = list.get(i).storeName;
                        storeInfo.storePhoneNum = list.get(i).storePhoneNum;
                        storeInfo.shoppingCarGoods = goodses;
                        storeInfos.add(storeInfo);
                    }

                }
                if (isEdit){
                    skus = skus.substring(0, skus.length()-1);
                    Log.i("skus", skus);
                    presenter.deleteGoods(skus);
                }else {
                    ConfirmOrderActivity.intentConfirmOrderActivity(getActivity(), storeInfos, "s");
                }
                break;
            case R.id.selected_all:
                for (StoreInfo storeInfo : list){
                    for (Goods goods : storeInfo.shoppingCarGoods){
                        goods.selected = !v.isSelected();
                    }
                }
                mAdapter.notifyDataSetChanged();
                v.setSelected(!v.isSelected());
                calculateMoney();
                break;
        }
    }


    public void getShoppingCar(){
        presenter.getData();
    }

    @Override
    public void setData(ShoppingCar data) {
        list = new ArrayList<>();
        list.addAll(data.effective);
        mAdapter.setNewData(list);
    }



    @Override
    public void deletedResult() {
        skus = "";
        presenter.getData();
        calculateMoney();
    }

    BaseQuickAdapter.OnItemChildClickListener storeClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            switch (view.getId()){
                case R.id.selected_store:
                    for (Goods goods:list.get(position).shoppingCarGoods){
                        goods.selected = !view.isSelected();
                    }
                    mAdapter.notifyDataSetChanged();
                    calculateMoney();
                    break;
            }
            return false;
        }
    };

    BaseQuickAdapter.OnItemChildClickListener goodsClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            switch (view.getId()){
                case R.id.choose_goods:
                    ((Goods) adapter.getData().get(position)).selected = !view.isSelected();
                    mAdapter.notifyDataSetChanged();
                    calculateMoney();
                    break;
                case R.id.subtract:
                    if (((Goods) adapter.getData().get(position)).itemNum != 1){
                        ((Goods) adapter.getData().get(position)).itemNum -= 1;
                        mAdapter.notifyDataSetChanged();
                        calculateMoney();
                    }
                    break;
                case R.id.add:
                    ((Goods) adapter.getData().get(position)).itemNum += 1;
                    mAdapter.notifyDataSetChanged();
                    calculateMoney();
                    break;
                case R.id.delete:
                    skus = ((Goods) adapter.getData().get(position)).sku;
                    presenter.deleteGoods(skus);
                    break;
            }

            return false;
        }
    };

    BaseQuickAdapter.OnItemLongClickListener goodsLongClickListener = new BaseQuickAdapter.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(final BaseQuickAdapter adapter, View view, final int position) {
            if (dialogDelete == null)
                dialogDelete = new DialogDelete(getActivity());
            dialogDelete.getDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    skus = ((Goods) adapter.getData().get(position)).sku;
                    presenter.deleteGoods(skus);
                }
            });
            dialogDelete.show();
            return false;
        }
    };

    private void calculateMoney(){
        float money = (float) 0.00;
        count = 0;
        for (StoreInfo storeInfo : list){
            for (Goods goods : storeInfo.shoppingCarGoods){
                if (goods.selected){
                    money = money + (Float.parseFloat(goods.shopPrice) * goods.itemNum);
                    count += 1;
                }
            }
        }
        total_money.setText(money + "");
        settle_accounts.setText(getString(R.string.shoppingcar_goods_count, count));

    }
}
