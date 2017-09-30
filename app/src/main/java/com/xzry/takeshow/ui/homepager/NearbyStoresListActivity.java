package com.xzry.takeshow.ui.homepager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.NearbyStoresEntity;
import com.xzry.takeshow.entity.request.NearbyStoresRequest;
import com.xzry.takeshow.ui.homepager.adapter.NearbyStoresListAdapter;
import com.xzry.takeshow.ui.homepager.contract.NearbyStoresContract;
import com.xzry.takeshow.ui.homepager.contract.component.DaggerNearbyStoresComponent;
import com.xzry.takeshow.ui.homepager.module.NearbyStoresModule;
import com.xzry.takeshow.ui.homepager.presenter.NearbyStoresPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 *  附近门店列表
 */
public class NearbyStoresListActivity extends BaseActivity implements NearbyStoresContract.View,View.OnClickListener{
    String TAG = "st";
    @BindView(R.id.ne_st_back)
    LinearLayout ll_back;

    @BindView(R.id.ne_st_tomap)
    LinearLayout ll_toMap;
    @BindView(R.id.ne_st_address)
    TextView tv_address;
    @BindView(R.id.ne_st_refresh)
    ImageView iv_refresh;
    @BindView(R.id.ne_st_recyclerview)
    RecyclerView recyclerView;
    @Inject
    NearbyStoresPresenter mPresenter;
    private NearbyStoresListAdapter nsl_adapter;
    public List<NearbyStoresEntity.row> rowList = new ArrayList<>();
    private LocationClient mLocClient;
    public String longitude;//经度
    public String latitude; //维度
    private int page = 1;
    private int status = 0;
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, NearbyStoresListActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_nearby_stores_list;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        ll_back.setOnClickListener(this);
        ll_toMap.setOnClickListener(this);
        iv_refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ne_st_refresh:
                status = 1 ;
                mLocClient.start();
                break;
            case R.id.ne_st_tomap:
                StoreActivity.intentActivity(this,rowList);
                break;
            case R.id.ne_st_back:
                finish();
                break;
//            case R.id.regist_head_pic_rciv:
//
//                break;
//            case R.id.regist_next_action_tv:
//
//                break;
            default:
                break;
        }
    }
    public void init(){
        DaggerNearbyStoresComponent.builder()
                .appComponent(getAppComponent())
                .nearbyStoresModule(new NearbyStoresModule(this))
                .build()
                .inject(this);
        initLocation();
    }

    /**
     *  定位
     */
    private void initLocation(){
        //定位初始化
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(new MyLocationListenner());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true); // 打开gps
        option.setIsNeedAddress(true);//
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(2000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mLocClient.requestLocation();//发送请求
    }

    @Override
    public void resultData(NearbyStoresEntity entity) {
        rowList = entity.rows;
        nsl_adapter = new NearbyStoresListAdapter(rowList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(nsl_adapter);

    }

    /**
     * 定位SDK监听函数
     */
    class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation location) {
            // map view 销毁后不在处理新接收的位置

            if (location == null) {
                return;
                //tv_locationCity.setText("定位失败");
            }
            mLocClient.stop();
                Log.i("list","---成功了");
               // Log.i("list","-----"+location.getPoiList().get(0).getName().toString());
                if (status == 1) {
                    showShortToast("刷新成功");
                }
                NearbyStoresListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_address.setText(location.getStreet());
                    }
                });
                longitude = Double.toString(location.getLongitude());//获取经度
                latitude = Double.toString(location.getLatitude());//获取维度
                Map mp = new HashMap();
                mp.put("longitude",longitude);
                mp.put("latitude",latitude);
                mp.put("pageNo",page);
                NearbyStoresRequest request = new NearbyStoresRequest();
                request.longitude  = longitude;
                request.latitude = latitude;
                request.pageNo = 1;
                mPresenter.toGetData(mp);
                Log.i(TAG,"请求数据");

        }



        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocClient != null) {
            mLocClient.stop();
        }
    }
}
