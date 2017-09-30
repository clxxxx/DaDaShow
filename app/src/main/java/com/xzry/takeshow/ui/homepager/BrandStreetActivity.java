package com.xzry.takeshow.ui.homepager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.xzry.takeshow.R;
import com.xzry.takeshow.dagger.component.DaggerBSActivityComponent;
import com.xzry.takeshow.dagger.module.BSPresenterModule;
import com.xzry.takeshow.entity.BrandStreet;
import com.xzry.takeshow.ui.homepager.adapter.BrandStreetAdapter;
import com.xzry.takeshow.ui.homepager.contract.BrandStreetContract;
import com.xzry.takeshow.ui.homepager.presenter.BrandStreetPresenter;
import com.xzry.takeshow.widget.FullyLinearLayoutManager;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 *  品牌街
 */
public class BrandStreetActivity extends BaseActivity implements BrandStreetContract.View{

    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.brand_recyclerview)
    RecyclerView recyclerView;

    @Inject
    BrandStreetPresenter presenter;

    private int pageNo = 1;

    private BrandStreetAdapter adapter;

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, BrandStreetActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_brand_street;
    }

    @Override
    protected void initView() {

        DaggerBSActivityComponent.builder()
                .appComponent(getAppComponent())
                .bSPresenterModule(new BSPresenterModule(this))
                .build()
                .inject(this);

        titleBarView.setTitle("品牌街");
        titleBarView.setRightImageResource(R.mipmap.ico_share);
        titleBarView.setRightTextVisibility(View.VISIBLE);

        final FullyLinearLayoutManager manager = new FullyLinearLayoutManager(this);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        manager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemLine(ScreenUtil.dip2px(this,10)));
        adapter = new BrandStreetAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getData(pageNo);
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
    public void setData(List<BrandStreet> data) {
//        data.clear();
        adapter.setNewData(data);
//        adapter.addHeaderView(addHeaderView());
        adapter.setEmptyView(addEmptyView());
    }


    private View addHeaderView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View carwaitView = inflater.inflate(R.layout.view_banner, null);
        ExpandImageView brand_img = (ExpandImageView) carwaitView.findViewById(R.id.brand_img);
        brand_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrandShopActivity.toStartActivity(BrandStreetActivity.this, "");
            }
        });
        return carwaitView;
    }

    private View addEmptyView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View carwaitView = inflater.inflate(R.layout.view_empty, null);
        return carwaitView;
    }

}
