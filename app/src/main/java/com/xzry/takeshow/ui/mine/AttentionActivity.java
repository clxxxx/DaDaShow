package com.xzry.takeshow.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerAttentionComponent;
import com.xzry.takeshow.dagger.module.AttentionModule;
import com.xzry.takeshow.entity.AttentionEntity;
import com.xzry.takeshow.ui.mine.adapter.AttentionAdapter;
import com.xzry.takeshow.ui.mine.contract.AttentionContract;
import com.xzry.takeshow.ui.mine.presenter.AttentionPresenter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 *  我的关注
 */
public class AttentionActivity extends BaseActivity implements AttentionContract.View{
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.attention_recyclerview)
    RecyclerView recyclerView;
    @Inject
    AttentionPresenter mPresenter;
    private int page = 1;
    public static void startActivity(Context context,int mark){
        Intent intent = new Intent(context,AttentionActivity.class);
        intent.putExtra("mark",mark);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_attention;
    }

    @Override
    protected void initView() {
        DaggerAttentionComponent.builder()
                .appComponent(getAppComponent())
                .attentionModule(new AttentionModule(this))
                .build()
                .inject(this);
        titleBarView.setRightImageResource(R.mipmap.ico_more2);
    }

    @Override
    protected void initData() {
        int mark = getIntent().getIntExtra("mark",0);
        if (mark == 0) {
            return;
        }else if (mark == 1) {
            titleBarView.setTitle("我的关注");
            mPresenter.toGetAttentionAll(BaseValue.TOKEN,page);
        }else if (mark == 2) {
            titleBarView.setTitle("我的粉丝");
            mPresenter.toGetFansAll(BaseValue.TOKEN,page);
        }

    }

    @Override
    protected void initListener() {
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
    public void resultData(AttentionEntity entity) {
        List<AttentionEntity.Item> list = entity.rows;
        AttentionAdapter adapter = new AttentionAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpaceItemLine(ScreenUtil.dip2px(this,1)));
        recyclerView.setAdapter(adapter);
    }
}
