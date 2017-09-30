package com.xzry.takeshow.ui.homepager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.statusBar.Eyes;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerBrandActivityComponent;
import com.xzry.takeshow.dagger.module.BrandPresenterModule;
import com.xzry.takeshow.entity.BrandHomeEntity;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.ui.homepager.adapter.HotGoodsAdapter;
import com.xzry.takeshow.ui.homepager.contract.BrandContract;
import com.xzry.takeshow.ui.homepager.presenter.BrandPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/9/8 0008.
 */

public class BrandActivity extends BaseActivity implements BrandContract.View, View.OnClickListener{

    @BindView(R.id.status_bar)
    View statusbar;
    @BindView(R.id.mtitle)
    LinearLayout mtitle;
    @BindView(R.id.brand_sp_back)
    ImageView back_icon;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.brand_sp_more)
    ImageView more_icon;
    @BindView(R.id.more)
    LinearLayout more;
    @BindView(R.id.brand_sp_search_lay)
    LinearLayout search_lay;
    @BindView(R.id.brand_goods_recyclerView)
    RecyclerView mRecyclerView;

    @Inject
    BrandPresenter presenter;

    private List<Goods> goodsList = new ArrayList<>();
    private String brandID;
    private HotGoodsAdapter adapter;

    public static void intentActivity(Context activity, String brandID) {
        Intent intent = new Intent(activity, BrandActivity.class);
        intent.putExtra("brandID", brandID);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_brand;
    }

    @Override
    protected void initView() {

        DaggerBrandActivityComponent.builder()
                .appComponent(getAppComponent())
                .brandPresenterModule(new BrandPresenterModule(this))
                .build()
                .inject(this);

        Eyes.setStatusBarLightMode(this, Color.WHITE, false);
        Eyes.translucentStatusBar(this, true);

        ViewTreeObserver viewTreeObserver = statusbar.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)statusbar.getLayoutParams();
                lp.height = BaseValue.STATUS_HEIGHT;
                statusbar.setLayoutParams(lp);
                return true;
            }
        });

        search_lay.setBackgroundResource(R.drawable.brand_search_bg_color2);
        search_lay.setAlpha((float) 0.3);

        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new HotGoodsAdapter(goodsList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy = totalDy + dy;
                if (totalDy >= 200){
                    Eyes.translucentStatusBar(BrandActivity.this, false);
                    mtitle.setBackgroundResource(R.color.white);
                    search_lay.setBackgroundResource(R.drawable.home_title_bar_search_corner_bg);
                    search_lay.setAlpha((float) 1);
                    back_icon.setBackgroundResource(R.mipmap.backarrow);
                    more_icon.setBackgroundResource(R.mipmap.ico_more2);
                }
                if (totalDy < 200){
                    Eyes.translucentStatusBar(BrandActivity.this, true);
                    mtitle.setBackgroundResource(R.color.transparent);
                    search_lay.setBackgroundResource(R.drawable.brand_search_bg_color2);
                    search_lay.setAlpha((float) 0.3);
                    back_icon.setBackgroundResource(R.mipmap.ico_back);
                    more_icon.setBackgroundResource(R.mipmap.ico_more);
                }
            }
        });
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void initData() {
        brandID = getIntent().getStringExtra("brandID");
        presenter.getData(brandID);
    }

    @Override
    protected void initListener() {
        back.setOnClickListener(this);
    }


    public View addTopView(String brandLogo, String brandRemark) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View carwaitView = inflater.inflate(R.layout.view_brand_topview, null);
        ExpandImageView brandLog = (ExpandImageView) carwaitView.findViewById(R.id.brand_log);
        brandLog.setImageURI(brandLogo);
        TextView brand_remark = (TextView) carwaitView.findViewById(R.id.brand_remark);
        brand_remark.setText(brandRemark);
        ExpandImageView brand_logs = (ExpandImageView) carwaitView.findViewById(R.id.brand_logs);
        brand_logs.setImageURI(brandLogo);
        return carwaitView;
    }

    @Override
    public void setData(BrandHomeEntity data) {
        goodsList.addAll(data.goods.rows);
        adapter.setNewData(goodsList);
        adapter.addHeaderView(addTopView(data.brandInfo.brandLogo, data.brandInfo.brandRemark));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }

    }
}
