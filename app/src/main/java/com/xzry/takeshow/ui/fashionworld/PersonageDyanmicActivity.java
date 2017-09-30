package com.xzry.takeshow.ui.fashionworld;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.util.statusBar.Eyes;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.RecommendEntity;
import com.xzry.takeshow.ui.fashionworld.adapter.PersonageDyanmicAdapter;
import com.xzry.takeshow.ui.homepager.widget.SpaceItemDecoration;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/23 0023.
 */

public class PersonageDyanmicActivity extends BaseActivity {

    @BindView(R.id.p_dyanmic_title)
    TitleBarView mtitle;
    @BindView(R.id.title_line)
    View title_line;
    @BindView(R.id.p_dyanmic_recyclerView)
    RecyclerView mRecyclerView;

    public static void intentPersonageDyanmicActivity(Context activity) {
        Intent intent = new Intent(activity, PersonageDyanmicActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_personage_dyanmic;
    }

    @Override
    protected void initView() {
        Eyes.setStatusBarLightMode(this, Color.WHITE, false);
        Eyes.translucentStatusBar(this, false);

        mtitle.setRightText("+关注");
        mtitle.getRightText().setVisibility(View.VISIBLE);
        mtitle.getRightText().setTextColor(getResources().getColor(R.color.white));
        mtitle.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick()
            {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        List<RecommendEntity> list = new ArrayList<>();
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_ONE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_TWO));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_TWO));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        list.add(new RecommendEntity(RecommendEntity.IMG_SIZE_NINE));
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new SpaceItemLine(ScreenUtil.dip2px(this,10)));
        PersonageDyanmicAdapter adapter = new PersonageDyanmicAdapter(list);
        adapter.addHeaderView(addTopView());
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
                if (totalDy >= 300){
                    Eyes.translucentStatusBar(PersonageDyanmicActivity.this, false);
                    title_line.setVisibility(View.VISIBLE);
                    mtitle.setLeftImageResource(R.mipmap.back_arrow);
                    mtitle.setBackgroundColor(Color.WHITE);
                    mtitle.getRightText().setTextColor(getResources().getColor(R.color.little_title_color));
                    mtitle.setAvatarImgVisibility(View.VISIBLE);
                }
                if (totalDy < 300){
                    Eyes.translucentStatusBar(PersonageDyanmicActivity.this, true);
                    title_line.setVisibility(View.GONE);
                    mtitle.setLeftImageResource(R.mipmap.ico_back);
                    mtitle.setBackgroundColor(Color.TRANSPARENT);
                    mtitle.getRightText().setTextColor(Color.WHITE);
                    mtitle.setAvatarImgVisibility(View.GONE);
                }
            }
        });
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public View addTopView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View carwaitView = inflater.inflate(R.layout.view_personage_dyanmic_top, null);
        return carwaitView;
    }
}
