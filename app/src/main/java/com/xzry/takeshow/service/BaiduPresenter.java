package com.xzry.takeshow.service;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.google.gson.Gson;
import com.sxjs.common.util.LogUtil;
import com.xzry.takeshow.R;

import static com.xzry.takeshow.base.BaseValue.update_position_time;

/**
 * Created by 周东阳 on 2017/9/16 0016.
 */

public class BaiduPresenter {

    private static LocationClient mLocationClient;
    private static BaiduPresenter instance;
    private boolean isFirstLocation = true;
    private static final String TAG = "BaiduPresenter";

    public static BaiduPresenter getInstance() {
        if (instance == null) {
            synchronized (BaiduPresenter.class) {
                if (instance == null) {
                    instance = new BaiduPresenter();
                }
            }
        }
        return instance;
    }

    public  LocationClient getLocationClient(Context context) {
        if (mLocationClient == null) {
            synchronized (BaiduPresenter.class) {
                if (mLocationClient == null) {
                    mLocationClient = new LocationClient(context);
                }
            }
        }
        return mLocationClient;
    }

    public void initLocation(Context context,BDLocationListener mBDLocationListener) {
        getLocationClient(context).registerLocationListener(mBDLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(update_position_time);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setIsNeedLocationPoiList(true);
        option.setIsNeedLocationDescribe(true);
        getLocationClient(context).setLocOption(option);
    }


    /**
     * 定位监听器
     */
    private class CurrentLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null) {
                return;
            }
            if (bdLocation.getLatitude() == 0.0 || bdLocation.getLongitude() == 0.0) {
                return;
            }

            int locType = bdLocation.getLocType();
            LogUtil.d(TAG, "locType==" + locType);
//            PreferencesUtil.getInstance().putString();
            Gson gson = new Gson();
            String jsonObject = gson.toJson(bdLocation);
//            PreferencesUtil.getInstance().putString(CURRENT_POSITION, jsonObject);
//            MyApplication.getInsatnce().sendBroadcast(new Intent(CURRENT_POSITION_NOFITY));
        }

//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
//        }


    }


    /**
     * @param location
     * @param zoom     缩放级别
     */
    public void navigateTo(BaiduMap mBaiduMap, BDLocation location, float zoom) {
        if (isFirstLocation) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            //此行定位用，不能省略
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(zoom);
            mBaiduMap.animateMapStatus(update);
            isFirstLocation = false;
        }
        //将自己显示在地图中
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
//        builder.accuracy(location.getRadius())
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(10000);
        MyLocationData locationData = builder.build();
        mBaiduMap.setMyLocationData(locationData);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.location_pin_select);
        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, bitmap);
        mBaiduMap.setMyLocationConfiguration(myLocationConfiguration);


    }

    public void setSignPosition(LatLng point, BaiduMap mBaiduMap) {
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.location_pin_select);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);

        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    /**
     * 对周边检索的范围进行绘制
     *
     * @param center
     * @param mBaiduMap
     */
    public void showNearbyArea(LatLng center, BaiduMap mBaiduMap) {
        BitmapDescriptor centerBitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.location_pin_select);
        MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap);
        mBaiduMap.addOverlay(ooMarker);

        OverlayOptions ooCircle = new CircleOptions().fillColor(0xCCCCCC00)
                .center(center).stroke(new Stroke(5, 0xFFFF00FF))
                .radius(0);
        mBaiduMap.addOverlay(ooCircle);
    }

    /**
     * 对区域检索的范围进行绘制
     *
     * @param bounds
     */
    public void showBound(LatLngBounds bounds, BaiduMap mBaiduMap) {
        BitmapDescriptor bdGround = BitmapDescriptorFactory
                .fromResource(R.mipmap.location_pin_select);

        OverlayOptions ooGround = new GroundOverlayOptions()
                .positionFromBounds(bounds).image(bdGround).transparency(0.8f);
        mBaiduMap.addOverlay(ooGround);

        MapStatusUpdate u = MapStatusUpdateFactory
                .newLatLng(bounds.getCenter());
        mBaiduMap.setMapStatus(u);

        bdGround.recycle();
    }

    /**
     * 设置中心可拖拽的点
     */
    public Marker setMarkDraggableCenter(LatLng latLng, BaiduMap mBaiduMap, int id) {


        setCenterPoint(latLng, mBaiduMap);
        BitmapDescriptor bdGround = BitmapDescriptorFactory
                .fromResource(id);
        OverlayOptions options = new MarkerOptions()
                .position(latLng)  //设置marker的位置
                .icon(bdGround)  //设置marker图标
                .zIndex(9)  //设置marker所在层级
                .animateType(MarkerOptions.MarkerAnimateType.grow)
                .period(10)
                .draggable(true);  //设置手势拖拽
        return (Marker) mBaiduMap.addOverlay(options);

    }

    /**
     * 设置覆盖物的点
     */
    public Marker setMarkDraggable(LatLng latLng, BaiduMap mBaiduMap, int id) {
        BitmapDescriptor bdGround = BitmapDescriptorFactory
                .fromResource(id);
        OverlayOptions options = new MarkerOptions()
                .position(latLng)  //设置marker的位置
                .icon(bdGround)  //设置marker图标
                .zIndex(9)  //设置marker所在层级
                .animateType(MarkerOptions.MarkerAnimateType.grow)
                .draggable(false);  //设置手势拖拽;

        return (Marker) mBaiduMap.addOverlay(options);

    }

    /**
     * 设置中心点
     */
    public void setCenterPoint(LatLng cenpt, BaiduMap mBaiduMap) {
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18.0f)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    /**
     * 将坐标转换成经纬度
     */
    public void latlngToAddress(LatLng latlng, GeoCoder geoCoder, final TextView tv) {
        if (latlng == null) {
            return;
        }
        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latlng));
        //设置地址或经纬度反编译后的监听,这里有两个回调方法,
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            //经纬度转换成地址
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    LogUtil.d(TAG, "找不到该地址!");
                }
                tv.setText(result.getAddress());
            }

            /**
             *  把地址转换成经纬度
             * @param result
             */

            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度
                String address = result.getAddress();
            }
        });
    }

    //设置地图缩放
    public void setZoom(LatLng llStart, LatLng llEnd, BaiduMap mBaiduMap) {
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(llStart)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        LatLngBounds bounds = new LatLngBounds.Builder().include(llStart).include(llEnd).build();
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(bounds);
        mBaiduMap.setMapStatus(u);
    }
    public void searchRoute(LatLng llStart, LatLng llEnd, OnGetRoutePlanResultListener onGetRoutePlanResultListener) {


        RoutePlanSearch search = RoutePlanSearch.newInstance();        //百度的搜索路线的类
        DrivingRoutePlanOption drivingRoutePlanOption = new DrivingRoutePlanOption();
        //起始坐标和终点坐标
        PlanNode startPlanNode = PlanNode.withLocation(llStart);  // lat  long
        PlanNode endPlanNode = PlanNode.withLocation(llEnd);
        drivingRoutePlanOption.from(startPlanNode);
        drivingRoutePlanOption.to(endPlanNode);
        search.drivingSearch(drivingRoutePlanOption);
        search.setOnGetRoutePlanResultListener(onGetRoutePlanResultListener);
    }

    /**
     * 显示弹出窗
     */
    public void showWindow(View view, BaiduMap mBaiduMap) {

        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(39.86923, 116.397428);
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

}
