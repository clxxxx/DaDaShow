package com.xzry.takeshow.ui.homepager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.CategoryEntity;
import com.xzry.takeshow.ui.homepager.adapter.CommodityClassicIconAdapter;
import com.xzry.takeshow.ui.homepager.adapter.TypeOfGoodsNameAdapter;
import com.xzry.takeshow.ui.homepager.contract.AllCCContract;
import com.xzry.takeshow.ui.homepager.contract.DaggerAllCCActivityComponent;
import com.xzry.takeshow.ui.homepager.module.AllPresenterModule;
import com.xzry.takeshow.ui.homepager.presenter.AllCommodityPresenter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**  分类
 * Created by 周东阳 on 2017/8/15 0015.
 */

public class AllCommdityClassifyActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, AllCCContract.View {

    @BindView(R.id.all_classify_title)
    TitleBarView mtitle;
    @BindView(R.id.classic_recycle)
    RecyclerView typeOfGoodsNameView;
    @BindView(R.id.classic_icon_view)
    RecyclerView classic_icon_view;
    private List<CategoryEntity> firstList = new ArrayList<>();
    private Map<String, List<CategoryEntity>> relationMap = new HashMap<>();
    private TypeOfGoodsNameAdapter mTypeAdapter;
    private CommodityClassicIconAdapter mIconadapter;

    private String checkParentKey;

    @Inject
    AllCommodityPresenter mPresenter;

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, AllCommdityClassifyActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_all_classify;
    }

    @Override
    protected void initView() {
        DaggerAllCCActivityComponent.builder()
                .appComponent(getAppComponent())
                .allPresenterModule(new AllPresenterModule(this))
                .build()
                .inject(this);

        mtitle.setTitle("全部分类");
        mtitle.setRightImageResource(R.mipmap.ico_more2);

        typeOfGoodsNameView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mTypeAdapter = new TypeOfGoodsNameAdapter(firstList);
        mTypeAdapter.setOnItemClickListener(this);
        mTypeAdapter.setSelectedPosition(0);
        typeOfGoodsNameView.setAdapter(mTypeAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        classic_icon_view.setLayoutManager(gridLayoutManager);
        mIconadapter = new CommodityClassicIconAdapter();
        classic_icon_view.setAdapter(mIconadapter);
        mIconadapter.setEmptyView(addEmptyView());

    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initListener() {
        mtitle.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    /**
     *上一个被点击的item
     */
    private int lastClikePosition = 0;
    private int recycleViewCanShowHeight;
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if(position == lastClikePosition)
            return;
        if (recycleViewCanShowHeight == 0){
            recycleViewCanShowHeight = typeOfGoodsNameView.getHeight();
        }
        if (recycleViewCanShowHeight > 0 && Build.VERSION.SDK_INT > 10){
            typeOfGoodsNameView.smoothScrollBy(0, (int) (view.getY() - recycleViewCanShowHeight/2 + view.getPivotY()));
        }
        this.mTypeAdapter.setSelectedPosition(position);
        adapter.notifyDataSetChanged();
        lastClikePosition = position;
        checkParentKey = firstList.get(position).categoryName;
        if (relationMap.get(checkParentKey) != null){
            mIconadapter.setNewData(relationMap.get(checkParentKey));
        }else {
            mPresenter.getSecondData(firstList.get(position).id);
        }

    }

    @Override
    public void setFirstData(List<CategoryEntity> list) {
        firstList.addAll(list);
        mTypeAdapter.setNewData(firstList);
        mPresenter.getSecondData(firstList.get(0).id);
        checkParentKey = firstList.get(0).categoryName;
    }

    @Override
    public void setSecondData(List<CategoryEntity> list) {
        List<CategoryEntity> secondList = new ArrayList<>();
        secondList.addAll(list);
        mIconadapter.setNewData(secondList);
        relationMap.put(checkParentKey, secondList);
    }


    private View addEmptyView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View carwaitView = inflater.inflate(R.layout.view_empty, null);
        return carwaitView;
    }
}
