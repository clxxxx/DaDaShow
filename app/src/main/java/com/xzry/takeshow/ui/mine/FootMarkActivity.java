package com.xzry.takeshow.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerFootMarkComponent;
import com.xzry.takeshow.dagger.module.FootMarkModule;
import com.xzry.takeshow.entity.FootMarkEntity;
import com.xzry.takeshow.ui.mine.adapter.FootMarkAdapter;
import com.xzry.takeshow.ui.mine.contract.FootMarkContract;
import com.xzry.takeshow.ui.mine.presenter.FootMarkPresenter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


/**
 *  足迹
 */
public class FootMarkActivity extends BaseActivity implements FootMarkContract.View,View.OnClickListener{
    @Inject
    FootMarkPresenter mPresenter;
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.foot_layout_botton)
    RelativeLayout re_layoutBottom;
    @BindView(R.id.foot_allSelect)
    CheckBox ck_allSelect;
    @BindView(R.id.foot_delete)
    TextView tv_delete;
    List<FootMarkEntity.Item> footList;
    public TextView titleRight;
    public FootMarkAdapter adapter;
    private int page = 1;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FootMarkActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_foot_mark;
    }

    @Override
    protected void initView() {
        DaggerFootMarkComponent.builder()
                .appComponent(getAppComponent())
                .footMarkModule(new FootMarkModule(this))
                .build()
                .inject(this);
        titleBarView.setTitle("我的足迹");
        titleRight = titleBarView.getRightText();
        titleRight.setText("编辑");
        titleRight.setId(1);
        titleRight.setTextColor(R.color.theme_dark_color);
        titleRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        Map mp = new HashMap();
        mp.put("token", BaseValue.TOKEN);
        mp.put("pageNo", page);
        mPresenter.getFootMarkList(mp);
    }

    @Override
    protected void initListener() {
        tv_delete.setOnClickListener(this);
        titleBarView.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        titleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleRight.getId() == 1) {
                    titleRight.setText("完成");
                    titleRight.setId(2);
                    re_layoutBottom.setVisibility(View.VISIBLE);
                    for (int i = 0; i < footList.size(); i++) {
                        footList.get(i).showStatus = true;
                    }
                    adapter.notifyDataSetChanged();
                } else if (titleRight.getId() == 2) {
                    titleRight.setText("编辑");
                    titleRight.setId(1);
                    re_layoutBottom.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public void getListSuccess(FootMarkEntity entity) {
        footList = entity.rows;
        adapter = new FootMarkAdapter(entity.rows);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void getDeleteSuccess() {
        titleRight.performClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foot_allSelect://全选
                toAllSelect();
                break;
            case R.id.foot_delete://删除
                toDelete();
                break;
            default:
                break;
        }
    }
    public void toAllSelect(){
        for (int i = 0; i < footList.size(); i++) {
            FootMarkEntity.Item item = footList.get(i);
            item.selectStatus = true;
        }
        adapter.notifyDataSetChanged();
    }

    public void toDelete() {
        String str = "";
        footList = adapter.getList();
        for (int i = 0; i < footList.size(); i++) {
            FootMarkEntity.Item item = footList.get(i);
            if (item.selectStatus) {
                str = str + item.sku+",";
            }
        }
        if (TextUtils.equals(str,"")) {
            showShortToast("你还没有选择宝贝哦！");
            return;
        }
        Map mp = new HashMap();
        mp.put("token",BaseValue.TOKEN);
        mp.put("skus",str);
        mPresenter.getDetele(mp);
    }
}

