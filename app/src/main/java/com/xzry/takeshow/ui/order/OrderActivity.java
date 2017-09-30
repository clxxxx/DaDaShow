package com.xzry.takeshow.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.OrderInfo;
import com.xzry.takeshow.entity.PageEntity;
import com.xzry.takeshow.ui.main.FragmentAdapter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/16 0016.
 */

public class OrderActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, OrderContract.View, BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.titlebar_dingdan)
    TitleBarView titleBarView;

    @BindView(R.id.order)
    TextView order;
    @BindView(R.id.wait_obligation)
    TextView wait_obligation;
    @BindView(R.id.wait_deliver)
    TextView wait_deliver;
    @BindView(R.id.wait_receiving)
    TextView wait_receiving;
    @BindView(R.id.wait_evaluate)
    TextView wait_evaluate;

    @BindView(R.id.tab_iv)
    View tab_iv;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private OrderFragment orderFragment;
    private WaitObligationFragment waitObligationFragment;
    private WaitDeliverFragment waitDeliverFragment;
    private WaitReceivingFragment waitReceivingFragment;
    private WaitEvaluateFragment waitEvaluateFragment;

    private List<OrderInfo> waitObligation = new ArrayList<>();//代付款
    private List<OrderInfo> waitDelivers = new ArrayList<>();//代发货
    private List<OrderInfo> waitReceiving = new ArrayList<>();//代收货
    private List<OrderInfo> waitEvaluate = new ArrayList<>();//待评价

    FragmentAdapter fragmentAdapter;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    private int pageNo = 1;

    @Inject
    OrderPresenter presenter;

    public static void intentOrderActivity(Activity activity, int position) {
        Intent intent = new Intent(activity, OrderActivity.class);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_mine_order;
    }

    @Override
    protected void initView() {

        DaggerOrderActivityComponent.builder()
                .appComponent(getAppComponent())
                .orderPresenterModule(new OrderPresenterModule(this))
                .build()
                .inject(this);

        titleBarView.setTitle("订单状态");
        titleBarView.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        fragmentList.add(orderFragment = new OrderFragment());
        fragmentList.add(waitObligationFragment = new WaitObligationFragment());
        fragmentList.add(waitDeliverFragment = new WaitDeliverFragment());
        fragmentList.add(waitReceivingFragment = new WaitReceivingFragment());
        fragmentList.add(waitEvaluateFragment = new WaitEvaluateFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragmentList);
        viewpager.setAdapter(fragmentAdapter);
        viewpager.setOffscreenPageLimit(5);

        //设置titlebar下划线的长度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tab_iv .getLayoutParams();
        lp.width = screenWidth / 5;
        tab_iv.setLayoutParams(lp);

    }

    @Override
    protected void initData() {
        currentIndex = getIntent().getIntExtra("position", 0);
        viewpager.setCurrentItem(currentIndex);
        switch (currentIndex)
        {
            case 0:
                order.setSelected(true);
                break;
            case 1:
                wait_obligation.setSelected(true);
                break;
            case 2:
                wait_deliver.setSelected(true);
                break;
            case 3:
                wait_receiving.setSelected(true);
                break;
            case 4:
                wait_evaluate.setSelected(true);
                break;
        }

        Map<String, String> map = new HashMap<>();
        map.put("token", BaseValue.TOKEN);
        map.put("state", currentIndex + "");
        map.put("pageNo", pageNo + "");
        presenter.getData(map);
    }

    @Override
    protected void initListener() {
        order.setOnClickListener(this);
        wait_evaluate.setOnClickListener(this);
        wait_receiving.setOnClickListener(this);
        wait_obligation.setOnClickListener(this);
        wait_deliver.setOnClickListener(this);

        viewpager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float offset, int offsetPixels) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tab_iv
                .getLayoutParams();
        if (currentIndex == position)
        {
            lp.leftMargin = (int) ( offset * (screenWidth * 1.0 / 5) + currentIndex
                    * (screenWidth / 5));
        }else if (currentIndex > position)
        {
            lp.leftMargin = (int) (-(1 - offset)
                    * (screenWidth * 1.0 / 5) + currentIndex
                    * (screenWidth / 5));
        }
        tab_iv.setLayoutParams(lp);
    }

    @Override
    public void onPageSelected(int position) {
        switch (position)
        {
            case 0:
                order.setSelected(true);
                wait_obligation.setSelected(false);
                wait_deliver.setSelected(false);
                wait_receiving.setSelected(false);
                wait_evaluate.setSelected(false);
                break;
            case 1:
                order.setSelected(false);
                wait_obligation.setSelected(true);
                wait_deliver.setSelected(false);
                wait_receiving.setSelected(false);
                wait_evaluate.setSelected(false);
                break;
            case 2:
                order.setSelected(false);
                wait_obligation.setSelected(false);
                wait_deliver.setSelected(true);
                wait_receiving.setSelected(false);
                wait_evaluate.setSelected(false);
                break;
            case 3:
                order.setSelected(false);
                wait_obligation.setSelected(false);
                wait_deliver.setSelected(false);
                wait_receiving.setSelected(true);
                wait_evaluate.setSelected(false);
                break;
            case 4:
                order.setSelected(false);
                wait_obligation.setSelected(false);
                wait_deliver.setSelected(false);
                wait_receiving.setSelected(false);
                wait_evaluate.setSelected(true);
                break;
        }
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.order:
                viewpager.setCurrentItem(0);
                break;
            case R.id.wait_obligation:
                viewpager.setCurrentItem(1);
                break;
            case R.id.wait_deliver:
                viewpager.setCurrentItem(2);
                break;
            case R.id.wait_receiving:
                viewpager.setCurrentItem(3);
                break;
            case R.id.wait_evaluate:
                viewpager.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void setView(PageEntity<OrderInfo> data) {

        for (OrderInfo orderInfo : data.rows){
            switch (orderInfo.state){
                case 1:
                    waitObligation.add(orderInfo);
                    break;
                case 2:
                    waitDelivers.add(orderInfo);
                    break;
                case 3:
                    waitReceiving.add(orderInfo);
                    break;
                case 4:
                    waitEvaluate.add(orderInfo);
                    break;
            }
        }
        orderFragment.setData(data.rows, this);
        waitObligationFragment.setData(waitObligation, this);
        waitDeliverFragment.setData(waitDelivers, this);
        waitReceivingFragment.setData(waitReceiving, this);
        waitEvaluateFragment.setData(waitEvaluate, this);
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


        return false;
    }
}
