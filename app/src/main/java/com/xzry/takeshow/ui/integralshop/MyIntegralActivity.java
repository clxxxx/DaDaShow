package com.xzry.takeshow.ui.integralshop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerMyIntegralComponent;
import com.xzry.takeshow.dagger.module.MyIntegralModule;
import com.xzry.takeshow.entity.IntegralInfo;
import com.xzry.takeshow.entity.MyIntegralEntity;
import com.xzry.takeshow.ui.integralshop.adapter.MyIntegralAdapter;
import com.xzry.takeshow.ui.integralshop.contract.MyIntegralContract;
import com.xzry.takeshow.ui.integralshop.presenter.MyIntegralPresenter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 *  我的积分
 */
public class MyIntegralActivity extends BaseActivity implements MyIntegralContract.View{
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.mi_in_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.myintegral_head_img)
    ExpandImageView iv_headImg;
    @BindView(R.id.myintegral_nickname)
    TextView tv_nickname;
    @BindView(R.id.myintegral_integral)
    TextView tv_integral;
    @BindView(R.id.myintegral_deduction)
    TextView tv_integralDeduction;
    @BindView(R.id.myintegral_deduction_count)
    TextView tv_integralDeductionCount;
    @Inject
    MyIntegralPresenter mPresenter;
    private int page = 1;
    @Override
    public int getLayout() {
        return R.layout.activity_mine_integral;
    }

    @Override
    protected void initView() {
        DaggerMyIntegralComponent.builder()
                .appComponent(getAppComponent())
                .myIntegralModule(new MyIntegralModule(this))
                .build()
                .inject(this);
        init();
    }

    @Override
    protected void initData() {
        mPresenter.getIntegralInfoData(BaseValue.TOKEN);
        mPresenter.getIntegralListData(BaseValue.TOKEN,page);
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
    public void init(){
        //StatusBarUtil.transparencyBar(this);
        titleBarView.setTitle("我的积分");
        titleBarView.setRightText("积分商城");
        titleBarView.setRightTextVisibility(View.VISIBLE);
        titleBarView.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivity(IntegralShopActivity.class);
            }
        });

    }
    @Override
    public void resultInfoData(IntegralInfo info) {
        if (info == null) {
            return;
        }
        iv_headImg.setImageURI(info.headerUrl);
        tv_nickname.setText(info.nickname);
        tv_integral.setText(info.point);
        tv_integralDeduction.setText(info.dAmountCount);
        tv_integralDeductionCount.setText(info.dOrderCount);
    }

    @Override
    public void resultListData(MyIntegralEntity entity) {
        List<MyIntegralEntity.Item> list = entity.rows;
        if (list.size() <= 0) {
            return;
        }
        MyIntegralAdapter adapter = new MyIntegralAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能6.
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemLine(1));
        recyclerView.setAdapter(adapter);
    }
}
