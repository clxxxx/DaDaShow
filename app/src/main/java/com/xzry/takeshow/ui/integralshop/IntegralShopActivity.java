package com.xzry.takeshow.ui.integralshop;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.xzry.takeshow.R;
import com.xzry.takeshow.dagger.component.DaggerIntegralShopComponent;
import com.xzry.takeshow.dagger.module.IntegralShopModule;
import com.xzry.takeshow.entity.homeEntity.IntegralCompEntity;
import com.xzry.takeshow.entity.homeEntity.IntegralEntity;
import com.xzry.takeshow.ui.homepager.adapter.FragmentListAdapter;
import com.xzry.takeshow.ui.integralshop.adapter.IntegralDialogAdapter;
import com.xzry.takeshow.ui.integralshop.contract.IntegralShopContract;
import com.xzry.takeshow.ui.integralshop.presenter.IntegralShopPresenter;
import com.xzry.takeshow.utils.PopupWindowUtil;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 *  积分商城
 */
public class IntegralShopActivity extends BaseActivity implements IntegralShopContract.View,View.OnClickListener{

    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.shop_inte_headimg)
    ImageView iv_headImg;
    @BindView(R.id.shop_tabs)
    TabLayout tab_layout;
    @BindView(R.id.shop_inte_unfold)
    LinearLayout lay_unfold;
    @BindView(R.id.shop_viewpager)
    ViewPager vp_viewpager;
    @Inject
    IntegralShopPresenter mPresenter;

    private List<String> titleLists = new ArrayList<>();

    public int page = 1;
    public List<IntegralCompEntity> lists = new ArrayList<>();
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, IntegralShopActivity.class);
        context.startActivity(intent);

    }
    @Override
    public int getLayout() {
        return R.layout.activity_integral_shop;
    }

    @Override
    protected void initView() {
        DaggerIntegralShopComponent.builder()
                .appComponent(getAppComponent())
                .integralShopModule(new IntegralShopModule(this))
                .build()
                .inject(this);

    }
    @Override
    protected void initData() {
        titleBarView.setTitle("积分商城");
        titleBarView.setRightImageResource(R.mipmap.ico_messages);
        titleBarView.setRightImageVisibility(View.VISIBLE);
        mPresenter.getAllData();
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
        lay_unfold.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == lay_unfold) {
            showAll();
        }
    }
    private void showAll() {
        View rootView = findViewById(R.layout.activity_setting);
        View view = this.getLayoutInflater().inflate(R.layout.integral_shop_dialog, null);
        final PopupWindowUtil pop = new PopupWindowUtil();
        int[] position = new int[2];
        lay_unfold.getLocationOnScreen(position);
        pop.showMarginTop(getWindow(),rootView,view,position[1]);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dialog_recycler);
        IntegralDialogAdapter adapter = new IntegralDialogAdapter(titleLists);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tab_layout.getTabAt(position).select();
                pop.dissPopWin();
            }
        });

    }
    @Override
    public void getResult(List<IntegralCompEntity> list) {
        lists = list;
        FragmentListAdapter adapter=new FragmentListAdapter(this.getSupportFragmentManager());
        for (int i = 0; i < list.size(); i++) {
            IntegralCompEntity item = list.get(i);
            adapter.addFragment(new IntegralTabOneFragment(item.categoryID),item.categoryName);
            titleLists.add(item.categoryName);
        }

        vp_viewpager.setAdapter(adapter);
        tab_layout.setupWithViewPager(vp_viewpager);
    }

    @Override
    public void getResultChild(IntegralEntity entity) {

    }
}
