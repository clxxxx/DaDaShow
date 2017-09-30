package com.xzry.takeshow.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerAddressComponent;
import com.xzry.takeshow.dagger.module.AddressModule;
import com.xzry.takeshow.entity.AddressEntity;
import com.xzry.takeshow.ui.mine.adapter.AddressShowAdapter;
import com.xzry.takeshow.ui.mine.contract.AddressContract;
import com.xzry.takeshow.ui.mine.presenter.AddressPresenter;
import com.xzry.takeshow.widget.TitleBarView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class AddressActivity extends BaseActivity implements AddressContract.View,View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.address_add)
    LinearLayout ll_add;
    @Inject
    AddressPresenter mPresenter;
    private AddressShowAdapter adapter;
    private List<AddressEntity> mlist;
    private AddressEntity item;
    public static int mark = 0;
    public static int ADDRESS_RESULT = 101;

    public static void initActivity(Context context){
        Intent intent = new Intent(context, AddressActivity.class);
        context.startActivity(intent);
    }

    public static void initActivityForResult(Activity activity, int requestCode){
        mark++;
        Intent intent = new Intent(activity, AddressActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        DaggerAddressComponent.builder()
                .appComponent(getAppComponent())
                .addressModule(new AddressModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        titleBarView.setTitle("选择收货地址");

    }

    @Override
    protected void initListener() {
        ll_add.setOnClickListener(this);
        titleBarView.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.address_add:
                toActivity(ADDaddressActivity.class);
                break;

            default:
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        showAllAddress();
    }

    //刷新界面
    @Override
    public void refreshDisplay(String str) {
        if (TextUtils.equals(str,"success")) {
            showAllAddress();
        }

    }
    //返回数据
    @Override
    public void resultData(final List<AddressEntity> list) {
        if (list.size() <= 0) {
            return;
        }
        mlist = list;
        adapter = new AddressShowAdapter(list);
        final LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setStackFromEnd(true);
        layout.setReverseLayout(true);//列表翻转

        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        if (mark > 0) {
            adapter.setOnItemClickListener(this);
        }

    }




    public void showAllAddress(){
        Map mp = new HashMap();
        mp.put("token",BaseValue.TOKEN);
        mPresenter.toGetAddress(mp);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_add_default_lay:
                toUpdateDefault(position);
                break;
            case R.id.item_add_compile:
                toCompile(position);
                break;
            case R.id.item_add_del:
                toDel(position);
                break;
            default:
                break;
        }
        return false;
    }
    private void toCompile(int position){
        getItem(position);
        Intent intent = new Intent(this,ADDaddressActivity.class);
        intent.putExtra("item", (Serializable) item);
        startActivity(intent);
    }
    private void toDel(int position){
        getItem(position);
        Map mp = new HashMap();
        mp.put("token",BaseValue.TOKEN);
        mp.put("consigneeId",item.id);
        mPresenter.toDelAddress(mp);
    }
    private void toUpdateDefault(int position){
        getItem(position);
        item.isDefault = 1;
        Map mp = new HashMap();
        mp.put("token", BaseValue.TOKEN);
        mp.put("orderConsignee", item.toString());
        mPresenter.toAddAddress(mp);
    }
    private void getItem(int position){
        if (mlist == null || mlist.size() <=0) {
            return;
        }
        item =  mlist.get(position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("address", mlist.get(position));
        this.setResult(ADDRESS_RESULT, intent);
        finish();
    }
}
