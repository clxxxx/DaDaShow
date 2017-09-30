package com.xzry.takeshow.ui.homepager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.headerview.HeaderView;
import com.sxjs.common.widget.pulltorefresh.PtrFrameLayout;
import com.sxjs.common.widget.pulltorefresh.PtrHandler;
import com.sxjs.common.widget.spaceitemline.SpaceItemLine;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.homeEntity.HomeEntity;
import com.xzry.takeshow.service.BaiduService;
import com.xzry.takeshow.ui.homepager.adapter.HomeMultipleRecycleAdapter;
import com.xzry.takeshow.ui.homepager.contract.HomeContract;
import com.xzry.takeshow.ui.homepager.contract.component.DaggerHomeFragmentComponent;
import com.xzry.takeshow.ui.homepager.module.HomePresenterModule;
import com.xzry.takeshow.ui.homepager.presenter.HomePresenter;
import com.xzry.takeshow.ui.message.MyMessageActivity;
import com.xzry.takeshow.utils.SpUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.R.id.list;
import static com.xzry.takeshow.base.BaseValue.CURRENT_POSITION;
import static com.xzry.takeshow.base.BaseValue.CURRENT_POSITION_NOFITY;

/**
 * Created by 周东阳 on 2017/7/31 0031.
 */

public class HomeFragment extends BaseFragment implements HeaderView.RefreshDistanceListener, HomeContract.View,View.OnClickListener{

    @BindView(R.id.home_head_view)
    HeaderView mPtrFrame;
    @BindView(R.id.home_location_lay)
    LinearLayout lay_location;
    @BindView(R.id.home_location_city)
    TextView tv_locationCity;
    @BindView(R.id.home_search_lay)
    LinearLayout lay_search;
    @BindView(R.id.home_message_lay)
    LinearLayout lay_message;
    @BindView(R.id.home_recyclerview)
    RecyclerView recyclerView;
    private HomeMultipleRecycleAdapter adapter;

    @Inject
    HomePresenter mPresenter;

    private HomeEntity[] homeEntities;

    private List<HomeEntity> homeList;

    //已完成请求的个数
    private int positionCount = 0;

    private int page = 1;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        DaggerHomeFragmentComponent.builder()
                .appComponent(getAppComponent())
                .homePresenterModule(new HomePresenterModule(this))
                .build()
                .inject(this);

        initPtrFrame();

        tv_locationCity.setText(SpUtil.getInstance().getString(BaseValue.User.CITY,"中国"));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new SpaceItemLine(ScreenUtil.dip2px(getContext(),10), getResources().getColor(R.color.activity_bg_color)));
        adapter = new HomeMultipleRecycleAdapter();
        adapter.setEnableLoadMore(true);
        recyclerView.setAdapter(adapter);

        //申请位置权限并获取位置
        if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }else{
            //开启定位服务
            getActivity().startService(new Intent(getActivity(), BaiduService.class));
        }
        //注册广播接受定位成功的位置
        getActivity().registerReceiver(mReceiver, new IntentFilter(CURRENT_POSITION_NOFITY));

    }

    @Override
    protected void initData() {
        homeList = new ArrayList<>();
        homeEntities = new HomeEntity[6];
        mPresenter.getHomeData(page);
    }

    @Override
    protected void initListener() {
        lay_location.setOnClickListener(this);
        lay_search.setOnClickListener(this);
        lay_message.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_location_lay:
                LocationActivity.initActivity(getActivity());
                break;
            case R.id.home_search_lay:
                SearchHomeActivity.initActivity(getActivity());
                break;
            case R.id.home_message_lay:
                MyMessageActivity.intentMyMessageActivity(getActivity());
                break;

            default:
                break;
        }
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String bdLocationStr = SpUtil.getInstance().getString(CURRENT_POSITION, null);
            if (!TextUtils.isEmpty(bdLocationStr)) {
                Gson gson = new Gson();
                BDLocation bdLocation = gson.fromJson(bdLocationStr, BDLocation.class);
                if (TextUtils.isEmpty(bdLocation.getLocationDescribe())) {
                    return;
                }
                tv_locationCity.setText(bdLocation.getCity());
            } else {
                getActivity().startService(new Intent(getActivity(), BaiduService.class));
            }
        }
    };

    /**
     * 初始化下拉刷新
     */
    private void initPtrFrame() {
        mPtrFrame.setOnRefreshDistanceListener(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }
        });
    }
    /**
     * 下拉后刷新数据
     */
    private void updateData() {
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.refreshComplete();
            }
        }, 3000);
    }


    @Override
    public void setHomeData(int position, HomeEntity data) {
        homeEntities[position] = data;
        positionCount += 1;
        if (positionCount == 6){
            mPtrFrame.refreshComplete();
            for (int i=0;i<homeEntities.length;i++){
                if (i == 1 && homeEntities[i].activities.size() != 0 || i == 2 && homeEntities[i].specials.rows.size() != 0
                        || i == 3 && homeEntities[i].specialRecommond.size() != 0 || i == 4 && homeEntities[i].hotbrand.size() != 0
                        || i == 5 && homeEntities[i] != null || i == 0)
                    homeList.add(homeEntities[i]);
            }
            adapter.setNewData(homeList);
        }
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onPositionChange(int currentPosY) {

    }
}
