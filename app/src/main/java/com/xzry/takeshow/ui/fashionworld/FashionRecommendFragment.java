package com.xzry.takeshow.ui.fashionworld;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.headerview.HeaderView;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.RecommendEntity;
import com.xzry.takeshow.ui.fashionworld.adapter.RecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



/**
 * Created by 周东阳 on 2017/8/7 0007.
 */

public class FashionRecommendFragment extends BaseFragment implements HeaderView.RefreshDistanceListener{

    @BindView(R.id.fashion_head_view)
    HeaderView mPtrFrame;
    @BindView(R.id.recommend_recyclerView)
    RecyclerView recommend_recyclerView;

    public static FashionRecommendFragment newInstance() {
        return new FashionRecommendFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        List<RecommendEntity> list = new ArrayList<>();
        list.add(new RecommendEntity(RecommendEntity.TOPIC));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_ONE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_TWO));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_TWO));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        recommend_recyclerView.setLayoutManager(new LinearLayoutManager(recommend_recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recommend_recyclerView.addItemDecoration(new SpaceItemLine(ScreenUtil.dip2px(getContext(),10)));
        RecommendAdapter adapter = new RecommendAdapter(list);
        recommend_recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mPtrFrame.setOnRefreshDistanceListener(this);
    }

    @Override
    public void onPositionChange(int currentPosY) {

    }
}
