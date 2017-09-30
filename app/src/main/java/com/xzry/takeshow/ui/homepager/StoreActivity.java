package com.xzry.takeshow.ui.homepager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.StatusBarUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.NearbyStoresEntity;
import com.xzry.takeshow.service.BaiduService;
import com.xzry.takeshow.utils.PopupWindowUtil;
import com.xzry.takeshow.utils.SpUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.xzry.takeshow.base.BaseValue.CURRENT_POSITION;
import static com.xzry.takeshow.base.BaseValue.CURRENT_POSITION_NOFITY;


/**
 * Created by Administrator on 2017/4/26 0026.
 * 门店主页面
 */

public class StoreActivity extends BaseActivity implements SensorEventListener,View.OnClickListener{
    String TAG = "store";
    //可视化地图
    @BindView(R.id.titlebar_back)
    ImageView iv_back;
    @BindView(R.id.titlebar_locationmap)
    ImageView iv_locationMap;


    // 定位相关
    boolean isFirstLoc = true; // 是否首次定位
    private Double lastX = 0.0;
    private LocationClient mLocClient;
    private int mCurrentDirection = 0;
    private double mLatitude = 0.0;
    private double mLongitude = 0.0;
    private float mCurrentAccracy;

    private MyLocationData locData;
    private BaiduMap mBaiduMap;
    private MapView mMapView = null;
    private List<NearbyStoresEntity.row> rowList;
    private NearbyStoresEntity.row item;
    public static void intentActivity(Context context,NearbyStoresEntity.row item) {
        Intent intent = new Intent(context, StoreActivity.class);
        intent.putExtra("market",1);
        intent.putExtra("item",(Serializable)item);
        context.startActivity(intent);
    }
    public static void intentActivity(Context context,List<NearbyStoresEntity.row> rowList){
        Intent intent = new Intent(context,StoreActivity.class);
        intent.putExtra("market",2);
        intent.putExtra("itemlist", (Serializable) rowList);
        context.startActivity(intent);
    }



    @Override
    public int getLayout() {
        return R.layout.stores_main;
    }

    @Override
    protected void initView() {
        StatusBarUtil.transparencyBar(this);
        Intent intent = getIntent();
        int market = intent.getIntExtra("market",0);
        if (market == 1) {
            item = (NearbyStoresEntity.row)intent.getSerializableExtra("item");
            rowList = new ArrayList<>();
            rowList.add(item);
        }else if (market == 2){
            rowList = (List<NearbyStoresEntity.row>) getIntent().getSerializableExtra("itemlist");
        }

        if (rowList == null || rowList.size() <= 0) {
            return;
        }
        Log.i(TAG,"----------"+rowList.size());

        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
        //开启定位
        initLocation();
        //显示覆盖物
        initMarker();


    }

    private void initLocation(){
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //开启定位服务
        this.startService(new Intent(this, BaiduService.class));
        //注册广播接受定位成功的位置
        this.registerReceiver(mReceiver, new IntentFilter(CURRENT_POSITION_NOFITY));
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

                mLongitude = bdLocation.getLongitude();
                mLatitude = bdLocation.getLatitude();
                
                mCurrentAccracy = bdLocation.getRadius();;
                locData = new MyLocationData.Builder()
                        .accuracy(bdLocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mCurrentDirection).latitude(mLatitude)
                        .longitude(mLongitude).build();
                mBaiduMap.setMyLocationData(locData);
                if (isFirstLoc) {

                    isFirstLoc = false;
                    LatLng ll = new LatLng(mLatitude,
                            mLongitude);
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(ll).zoom(18.0f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

                }
                
            } else {
                StoreActivity.this.startService(new Intent(StoreActivity.this, BaiduService.class));
            }
        }
    };


    /**
     * 自定义marker
     * @param
     */
    public void initMarker() {
        mBaiduMap.clear();

        for (int i = 0; i < rowList.size(); i++) {

            final View markView = LayoutInflater.from(this).inflate(R.layout.store_mapinfo, null);
            final ExpandImageView storeImg = (ExpandImageView) markView.findViewById(R.id.item_st_map_img);
            final NearbyStoresEntity.row item = rowList.get(i);
            String imageUrl = item.storeImg;
            ImageLoader.getInstance().loadImage(imageUrl, new ImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view,
                                            FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    //storeImg.setImageBitmap(loadedImage);
                    storeImg.setImageURI(imageUri);

                    Bitmap bitmap = getViewBitmap(markView);
                    BitmapDescriptor bitmapDes = BitmapDescriptorFactory.fromBitmap(bitmap);
                    LatLng pointA = new LatLng(item.latitude, item.longitude);
                    OverlayOptions option = new MarkerOptions()
                            .position(pointA)
                            .anchor(0.34f, 1.0f)
                            .icon(bitmapDes);
                    Marker marker = (Marker) mBaiduMap.addOverlay(option);
                    //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
                    Bundle bundle = new Bundle();
                    //info必须实现序列化接口
                    bundle.putSerializable("info", item);
                    marker.setExtraInfo(bundle);


                    // 初始化点聚合管理类
//                    ClusterManager mClusterManager = new ClusterManager<>(this, mBaiduMap);
//                    // 向点聚合管理类中添加Marker实例
//                    LatLng llA = new LatLng(39.963175, 116.400244);
//                    List<MyItem> items = new ArrayList<>();
//                    items.add(new MyItem(llA));
//                    mClusterManager.addItems(items);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        }
//        //将地图显示在最后一个marker的位置
//        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
//        mBaiduMap.setMapStatus(msu);

        //添加marker点击事件的监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //从marker中获取info信息
                Bundle bundle = marker.getExtraInfo();
                final NearbyStoresEntity.row item  = (NearbyStoresEntity.row) bundle.getSerializable("info");
                View view = getLayoutInflater().inflate(R.layout.item_market_bottom,null);
                PopupWindowUtil pop = new PopupWindowUtil();
                pop.showBottom(getWindow(),getWindow().getDecorView(),view);

                TextView tv_dist = (TextView) view.findViewById(R.id.market_bottom_dist);
                TextView tv_city = (TextView) view.findViewById(R.id.market_bottom_city);
                TextView tv_address = (TextView) view.findViewById(R.id.market_bottom_address);
                TextView tv_info = (TextView) view.findViewById(R.id.market_bottom_info);
                TextView tv_storeName = (TextView) view.findViewById(R.id.market_bottom_store_name);
                TextView tv_mobile = (TextView) view.findViewById(R.id.market_bottom_mobile);
                tv_dist.setText(item.distance);
                tv_address.setText(item.address);
                tv_storeName.setText(item.storeName);
                tv_mobile.setText(item.mobile.substring(0,4)+" - "+item.mobile.substring(4,item.mobile.length()));
                TextView tv_go = (TextView) view.findViewById(R.id.market_bottom_go);
                tv_go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        // 驾车路线规划
                        intent.setData(Uri.parse("baidumap://map/navi?location="+item.latitude+","+item.longitude));
                        startActivity(intent);
                    }
                });



                return true;
            }
        });




        //  for (int i = 0; i < rowList.size(); i++) {


//        NearbyStoresEntity.row  row= rowList.get(7);
//        //下载具体操作
//
//
//        Log.i(TAG,"----------"+row.storeImg);
//        //expandImageView.setImageURI(row.storeImg);

//        markView.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//
//        storeImg.setImageURI(row.storeImg);
//        Uri uri =Uri.parse(row.storeImg);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setControllerListener(listener)
//                .build();
//
//        storeImg.setController(controller);

        //Glide.with(this).load(row.storeImg).into(storeImg);
//        //expandImageView.setImageURI(row.storeImg);

//       // mMapView.addView(markView);


////        //构建MarkerOption，用于在地图上添加Marker
//        MarkerOptions ooA = new MarkerOptions().position(pointA).icon(bitmapDes).anchor(0.5f, 0.5f);
//        MarkerOptions ooB = new MarkerOptions().position(pointA).icon(bitmapDes).anchor(0.5f, 0.5f);
//

////
//        List<OverlayOptions> optionsLists = new ArrayList<>();
//        optionsLists.add(option1);
//        optionsLists.add(option2);
//
//        View view = getLayoutInflater().inflate(R.layout.custom_text_view, null);
//        // 将View转化成用于显示的bitmap
//        // View viewCache = getLayoutInflater().inflate(R.layout.custom_text_view,null);
//        // View popup1 = (View) viewCache.findViewById(R.id.popleft);
//        // View popup2 = (View) viewCache.findViewById(R.id.popright);
//        // Bitmap[] bitMaps = { BMapUtil.getBitmapFromView(popup1)};
//        // Bitmap[] bitMaps2 = {BMapUtil.getBitmapFromView(popup2)};
//        // pop.showPopup(bitMaps, p1, 32); pop2.showPopup(bitMaps2, p2, 32);
//        // pItems.add(pop);// pItems.add(pop2);


//        //在地图上添加Marker，并显示
//        mBaiduMap.addOverlay(option1);

//        //mMapView.invalidate();


    }

    private Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);

        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());

        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mLatitude)
                    .longitude(mLongitude).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }






    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        iv_back.setOnClickListener(this);
        iv_locationMap.setOnClickListener(this);
        //tv_normalMode.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();

        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理

    }
    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理

    }

    @Override
    protected void onDestroy() {
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        // 退出时销毁定位
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();

    }

    



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.titlebar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
