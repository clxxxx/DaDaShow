package com.xzry.takeshow.ui.eventtopic;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.xzry.takeshow.R;
import com.xzry.takeshow.dagger.component.DaggerEventTopicComponent;
import com.xzry.takeshow.dagger.module.EventTopicModule;
import com.xzry.takeshow.entity.homeEntity.EventTopicComp;
import com.xzry.takeshow.entity.homeEntity.EventTopicEntity;
import com.xzry.takeshow.ui.homepager.adapter.FragmentListAdapter;
import com.xzry.takeshow.ui.integralshop.adapter.IntegralDialogAdapter;
import com.xzry.takeshow.utils.PopupWindowUtil;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 *  活动专题
 */
public class EventTopicActivity extends BaseActivity implements EventtopicContract.View,View.OnClickListener{
    String TAG = "EventTopicActivity";
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
    @BindView(R.id.top_layout)
    LinearLayout ll_layout;
    @Inject
    EventTopicPresenter mPresenter;
    public static int screenW, screenH;
    private LayoutInflater mInflater;
    public String titleName;
    public String subjectID;
    public int page = 1;
    private List<String> titleLists = new ArrayList<>();



    public static void toActivity(Context context, String titlename, String subjectiD) {
        Intent intent = new Intent(context, EventTopicActivity.class);
        intent.putExtra("titlename", titlename);
        intent.putExtra("subjectiD", subjectiD);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_event_topic;
    }

    @Override
    protected void initView() {
        DaggerEventTopicComponent.builder()
                .appComponent(getAppComponent())
                .eventTopicModule(new EventTopicModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void initData() {
        titleName = getIntent().getStringExtra("titleName");
        subjectID = getIntent().getStringExtra("subjectID");
        titleBarView.setTitle(titleName);
        titleBarView.setRightImageResource(R.mipmap.ico_messages);
        titleBarView.setRightImageVisibility(View.VISIBLE);
        mPresenter.getAllData(subjectID);
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
        ll_layout.getLocationOnScreen(position);
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
    public void getResult(List<EventTopicComp> list) {
        FragmentListAdapter adapter=new FragmentListAdapter(this.getSupportFragmentManager());
        for (int i = 0; i < list.size(); i++) {
            EventTopicComp item = list.get(i);
            adapter.addFragment(new EventTabOneFragment(item.categoryID,item.subjectID),item.categoryName);
            titleLists.add(item.categoryName);
        }

        vp_viewpager.setAdapter(adapter);
        tab_layout.setupWithViewPager(vp_viewpager);
    }

    @Override
    public void getResultChild(EventTopicEntity entity) {

    }




}
