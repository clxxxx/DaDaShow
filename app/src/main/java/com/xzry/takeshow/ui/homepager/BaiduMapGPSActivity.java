package com.xzry.takeshow.ui.homepager;

import com.sxjs.common.base.BaseActivity;

/**
 *  百度地图导航
 */
public class BaiduMapGPSActivity extends BaseActivity {
    String TAG = "baidu";

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
//    /**
//     * SD卡的路径
//     */
//
//
//    private BNRoutePlanNode mBNRoutePlanNode = null;
//    private String mSDCardPath = null;
//    private boolean hasInitSuccess = false;
//    private static final String APP_FOLDER_NAME = "intvehapp";
//    private boolean hasRequestComAuth = false;
//    private final static String authComArr[] = { Manifest.permission.READ_PHONE_STATE };
//    private final static int authBaseRequestCode = 1;
//    private final static int authComRequestCode = 2;
//    // 是否使用通用接口
//    private boolean useCommonInterface = true;
//
//    private BaiduNaviCommonModule mBaiduNaviCommonModule = null;
//
//
//    private double mLatitude = 0.0;
//    private double mLongitude = 0.0;
//    private String mStartName;
//    private NearbyStoresEntity.row item;
//    public static void startActivity(Context context, Object obj){
//        Intent intent = new Intent(context,BaiduMapGPSActivity.class);
//        intent.putExtra("item", (Serializable) obj);
//        context.startActivity(intent);
//    }
//    @Override
//    public int getLayout() {
//        return R.layout.activity_baidu_map_gps;
//    }
//
//    @Override
//    protected void initView() {
//        item = (NearbyStoresEntity.row) getIntent().getSerializableExtra("item");
//        //开启定位
//        initLocation();
//
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initListener() {
//
//    }
//    private void initLocation() {
//        //开启定位服务
//        this.startService(new Intent(this, BaiduService.class));
//        //注册广播接受定位成功的位置
//        this.registerReceiver(mReceiver, new IntentFilter(CURRENT_POSITION_NOFITY));
//    }
//    BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String bdLocationStr = SpUtil.getInstance().getString(CURRENT_POSITION, null);
//            if (!TextUtils.isEmpty(bdLocationStr)) {
//                Gson gson = new Gson();
//                BDLocation bdLocation = gson.fromJson(bdLocationStr, BDLocation.class);
//                if (TextUtils.isEmpty(bdLocation.getLocationDescribe())) {
//                    return;
//                }
//                mLongitude = bdLocation.getLongitude();
//                mLatitude = bdLocation.getLatitude();
//                mStartName = bdLocation.getAddrStr();
//                if (initDirs()) {
//                    /**
//                     * 使用SDK前，先进行百度服务授权和引擎初始化。
//                     */
//                    initNavi();
//                }
//
//            } else {
//                BaiduMapGPSActivity.this.startService(new Intent(BaiduMapGPSActivity.this, BaiduService.class));
//            }
//        }
//    };
//    /**
//     * 初始化SD卡，在SD卡路径下新建文件夹：App目录名，文件中包含了很多东西，比如log、cache等等
//     * @return
//     */
//    private boolean initDirs() {
//        mSDCardPath = getSdcardDir();
//        if (mSDCardPath == null) {
//            return false;
//        }
//        File f = new File(mSDCardPath, APP_FOLDER_NAME);
//        if (!f.exists()) {
//            try {
//                f.mkdir();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return true;
//    }
//
//    String authinfo = null;
//
//    /**
//     * 使用SDK前，先进行百度服务授权和引擎初始化
//     */
//    private void initNavi() {
//
//        BNOuterTTSPlayerCallback ttsCallback = null;
//
//        BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME, new BaiduNaviManager.NaviInitListener() {
//            @Override
//            public void onAuthResult(int status, String msg) {
//                if (0 == status) {
//                    authinfo = "key校验成功!";
//                } else {
//                    authinfo = "key校验失败, " + msg;
//                }
//                BaiduMapGPSActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Toast.makeText(BaiduMapGPSActivity.this, authinfo, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            public void initSuccess() {
//                Toast.makeText(BaiduMapGPSActivity.this, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
//                hasInitSuccess = true;
//
//                initSetting();
//            }
//
//            public void initStart() {
//                Toast.makeText(BaiduMapGPSActivity.this, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
//            }
//
//            public void initFailed() {
//                Toast.makeText(BaiduMapGPSActivity.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
//            }
//
//
//        },  null, ttsHandler, null);
//
//    }
//    /**
//     * 内部TTS播报状态回传handler
//     */
//    private Handler ttsHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            int type = msg.what;
//            switch (type) {
//                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
//                    showToastMsg("Handler : TTS play start");
//                    break;
//                }
//                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
//                    showToastMsg("Handler : TTS play end");
//                    break;
//                }
//                default :
//                    break;
//            }
//        }
//    };
//
//
//
//
//    private String getSdcardDir() {
//        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
//            return Environment.getExternalStorageDirectory().toString();
//        }
//        return null;
//    }
//
//    /**
//     * 内部TTS播报状态回调接口
//     */
//    private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {
//
//        @Override
//        public void playEnd() {
////            showToastMsg("TTSPlayStateListener : TTS play end");
//        }
//
//        @Override
//        public void playStart() {
////            showToastMsg("TTSPlayStateListener : TTS play start");
//        }
//    };
//
//    public void showToastMsg(final String msg) {
//        BaiduMapGPSActivity.this.runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                Toast.makeText(BaiduMapGPSActivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    /**
//     * 导航设置管理器
//     */
//    private void initSetting(){
////        // BNaviSettingManager.setDayNightMode(BNaviSettingManager.DayNightMode.DAY_NIGHT_MODE_DAY);
////        BNaviSettingManager
////                .setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
////        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
////        // BNaviSettingManager.setPowerSaveMode(BNaviSettingManager.PowerSaveMode.DISABLE_MODE);
////        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
////        BNaviSettingManager.setIsAutoQuitWhenArrived(true);
////        Bundle bundle = new Bundle();
////        // 必须设置APPID，否则会静音
////        bundle.putString(BNCommonSettingParam.TTS_APP_ID, "9354030");
////        BNaviSettingManager.setNaviSdkParam(bundle);
//
//
//        /**
//         * 日夜模式 1：自动模式 2：白天模式 3：夜间模式
//         */
//        BNaviSettingManager.setDayNightMode(BNaviSettingManager.DayNightMode.DAY_NIGHT_MODE_DAY);
//        /**
//         * 设置全程路况显示
//         */
//        BNaviSettingManager.setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
//        /**
//         * 设置语音播报模式
//         */
//        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
//        /**
//         * 设置省电模式
//         */
//        BNaviSettingManager.setPowerSaveMode(BNaviSettingManager.PowerSaveMode.DISABLE_MODE);
//        /**
//         * 设置实时路况条
//         */
//        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
//
//        routeplanToNavi();
//    }
//
//
//
//    private boolean hasCompletePhoneAuth() {
//        // TODO Auto-generated method stub
//
//        PackageManager pm = this.getPackageManager();
//        for (String auth : authComArr) {
//            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void routeplanToNavi() {
//        if (!hasInitSuccess) {
//            Toast.makeText(BaiduMapGPSActivity.this, "还未初始化!", Toast.LENGTH_SHORT).show();
//        }
//        // 权限申请
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            // 保证导航功能完备
//            if (!hasCompletePhoneAuth()) {
//                if (!hasRequestComAuth) {
//                    hasRequestComAuth = true;
//                    this.requestPermissions(authComArr, authComRequestCode);
//                    return;
//                } else {
//                    Toast.makeText(BaiduMapGPSActivity.this, "没有完备的权限!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        }
////         = null;
////        BNRoutePlanNode eNode = null;
//        //GeoPoint ptGCJStart = CoordinateTransformUtil.transferBD09ToGCJ02(locLong, locLat);
//
//        BNRoutePlanNode sNode = new BNRoutePlanNode(mLongitude, mLatitude, mStartName, null, BNRoutePlanNode.CoordinateType.BD09LL );
//        BNRoutePlanNode eNode = new BNRoutePlanNode(item.longitude, item.latitude, item.storeName, null, BNRoutePlanNode.CoordinateType.BD09LL);
//
//
//
//        //if (sNode != null && eNode != null) {
//            List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
//            list.add(sNode);
//            list.add(eNode);
//
//            // 开发者可以使用旧的算路接口，也可以使用新的算路接口,可以接收诱导信息等
//            // BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));
//            BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode),
//                    eventListerner);
//        //}
//    }
//    BaiduNaviManager.NavEventListener eventListerner = new BaiduNaviManager.NavEventListener() {
//
//        @Override
//        public void onCommonEventCall(int what, int arg1, int arg2, Bundle bundle) {
//            BNEventHandler.getInstance().handleNaviEvent(what, arg1, arg2, bundle);
//        }
//    };
//
//    /**
//     * 导航回调监听器
//     */
//    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {
//
//
//        public DemoRoutePlanListener(BNRoutePlanNode node) {
//            mBNRoutePlanNode = node;
//        }
//
//        @Override
//        public void onJumpToNavigator() {
//            /*
//             * 设置途径点以及resetEndNode会回调该接口
//             */
//
////            for (Activity ac : activityList) {
////
////                if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {
////
////                    return;
////                }
////            }
//            /**
////             * 导航activity
////             */
////            Intent intent = new Intent(BaiduMapGPSActivity.this, BNDemoGuideActivity.class);
////            Bundle bundle = new Bundle();
////            bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
////            intent.putExtras(bundle);
////            startActivity(intent);
//            createHandler();
//
//
//
//            View view = null;
//            if (useCommonInterface) {
//                //使用通用接口
//                mBaiduNaviCommonModule = NaviModuleFactory.getNaviModuleManager().getNaviCommonModule(
//                        NaviModuleImpl.BNaviCommonModuleConstants.ROUTE_GUIDE_MODULE, BaiduMapGPSActivity.this,
//                        BNaviBaseCallbackModel.BNaviBaseCallbackConstants.CALLBACK_ROUTEGUIDE_TYPE, mOnNavigationListener);
//                if(mBaiduNaviCommonModule != null) {
//                    mBaiduNaviCommonModule.onCreate();
//                    view = mBaiduNaviCommonModule.getView();
//                }
//
//            } else {
//                //使用传统接口
//                view = BNRouteGuideManager.getInstance().onCreate(BaiduMapGPSActivity.this,mOnNavigationListener);
//            }
//
//
//            if (view != null) {
//                setContentView(view);
//            }
//
//
//            //显示自定义图标
//            if (hd != null) {
//                hd.sendEmptyMessageAtTime(MSG_SHOW, 5000);
//            }
//
//            BNEventHandler.getInstance().getDialog(BaiduMapGPSActivity.this);
//            BNEventHandler.getInstance().showDialog();
//
//        }
//
//        @Override
//        public void onRoutePlanFailed() {
//            // TODO Auto-generated method stub
//            Toast.makeText(BaiduMapGPSActivity.this, "算路失败", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(useCommonInterface) {
//            if(mBaiduNaviCommonModule != null) {
//                mBaiduNaviCommonModule.onResume();
//            }
//        } else {
//            BNRouteGuideManager.getInstance().onResume();
//        }
//
//
//
//    }
//
//    private BNRouteGuideManager.OnNavigationListener mOnNavigationListener = new BNRouteGuideManager.OnNavigationListener() {
//
//        @Override
//        public void onNaviGuideEnd() {
//            //退出导航
//            finish();
//        }
//
//        @Override
//        public void notifyOtherAction(int actionType, int arg1, int arg2, Object obj) {
//
//            if (actionType == 0) {
//                //导航到达目的地 自动退出
//                Log.i(TAG, "notifyOtherAction actionType = " + actionType + ",导航到达目的地！");
//            }
//
//            Log.i(TAG, "actionType:" + actionType + "arg1:" + arg1 + "arg2:" + arg2 + "obj:" + obj.toString());
//        }
//
//    };
//
//    private static final int MSG_SHOW = 1;
//    private static final int MSG_HIDE = 2;
//    private static final int MSG_RESET_NODE = 3;
//    private Handler hd = null;
//
//    private void createHandler() {
//        if (hd == null) {
//            hd = new Handler(getMainLooper()) {
//                public void handleMessage(android.os.Message msg) {
//                    if (msg.what == MSG_SHOW) {
//                        addCustomizedLayerItems();
//                    } else if (msg.what == MSG_HIDE) {
//                        BNRouteGuideManager.getInstance().showCustomizedLayer(false);
//                    } else if (msg.what == MSG_RESET_NODE) {
//                        BNRouteGuideManager.getInstance().resetEndNodeInNavi(
//                                new BNRoutePlanNode(116.21142, 40.85087, "百度大厦11", null, BNRoutePlanNode.CoordinateType.GCJ02));
//                    }
//                };
//            };
//        }
//    }
//
//    private void addCustomizedLayerItems() {
//        List<BNRouteGuideManager.CustomizedLayerItem> items = new ArrayList<BNRouteGuideManager.CustomizedLayerItem>();
//        BNRouteGuideManager.CustomizedLayerItem item1 = null;
//        if (mBNRoutePlanNode != null) {
//            item1 = new BNRouteGuideManager.CustomizedLayerItem(mBNRoutePlanNode.getLongitude(), mBNRoutePlanNode.getLatitude(),
//                    mBNRoutePlanNode.getCoordinateType(), getResources().getDrawable(R.drawable.ic_camera),
//                    BNRouteGuideManager.CustomizedLayerItem.ALIGN_CENTER);
//            items.add(item1);
//
//            BNRouteGuideManager.getInstance().setCustomizedLayerItems(items);
//        }
//        BNRouteGuideManager.getInstance().showCustomizedLayer(true);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    private BNOuterTTSPlayerCallback mTTSCallback = new BNOuterTTSPlayerCallback() {
//
//        @Override
//        public void stopTTS() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "stopTTS");
//        }
//
//        @Override
//        public void resumeTTS() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "resumeTTS");
//        }
//
//        @Override
//        public void releaseTTSPlayer() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "releaseTTSPlayer");
//        }
//
//        @Override
//        public int playTTSText(String speech, int bPreempt) {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "playTTSText" + "_" + speech + "_" + bPreempt);
//
//            return 1;
//        }
//
//        @Override
//        public void phoneHangUp() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "phoneHangUp");
//        }
//
//        @Override
//        public void phoneCalling() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "phoneCalling");
//        }
//
//        @Override
//        public void pauseTTS() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "pauseTTS");
//        }
//
//        @Override
//        public void initTTSPlayer() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "initTTSPlayer");
//        }
//
//        @Override
//        public int getTTSState() {
//            // TODO Auto-generated method stub
//            Log.e("test_TTS", "getTTSState");
//            return 1;
//        }
//    };


















//    String TAG = "gps";
//    private BNRoutePlanNode.CoordinateType mCoordinateType;
//    private boolean hasInitSuccess = false;
//    private boolean hasRequestComAuth = false;
//    private final static String authBaseArr[] =
//            { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION };
//    private final static String authComArr[] = { Manifest.permission.READ_PHONE_STATE };
//    private final static int authBaseRequestCode = 1;
//    private final static int authComRequestCode = 2;
//
//    private NearbyStoresEntity.row item;
//
//    private BaiduMap mBaiduMap;
//    private MapView mMapView = null;
//    private LocationClient mLocClient;
//    private int mCurrentDirection = 0;
//    private double mCurrentLat = 0.0;
//    private double mCurrentLon = 0.0;
//    private float mCurrentAccracy;
//    // 是否使用通用接口
//    private boolean useCommonInterface = true;
//    private MyLocationData locData;
//    BaiduNaviCommonModule mBaiduNaviCommonModule;
//
//    private Button mWgsNaviBtn = null;
//    private Button mGcjNaviBtn = null;
//    private Button mBdmcNaviBtn = null;
//    private Button mDb06ll = null;
//    private String mSDCardPath = null;
//    String authinfo = null;
//    private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";
//
//    public static void startActivity(Context context,Object obj){
//        Intent intent = new Intent(context,BaiduMapGPSActivity.class);
//        intent.putExtra("item", (Serializable) obj);
//        context.startActivity(intent);
//    }
//    @Override
//    public int getLayout() {
//        return R.layout.activity_baidu_map_gps;
//    }
//
//    @Override
//    protected void initView() {
//        item = (NearbyStoresEntity.row) getIntent().getSerializableExtra("item");
//        Log.i(TAG,item.storeImg);
//        mMapView = (MapView) findViewById(R.id.bmapView);
//        mBaiduMap = mMapView.getMap();
//        //普通地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
//        mBaiduMap.setMapStatus(msu);
//        //开启定位
//        initLocation();
////        if (initDirs()) {
////            initNavi();
////        }
//
//
//
//    }
//    private void initLocation(){
//        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        // 定位初始化
//        mLocClient = new LocationClient(this);
//        mLocClient.registerLocationListener(new MyLocationListenner());
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(0);
//        mLocClient.setLocOption(option);
//        mLocClient.start();
//
//    }
//    private boolean hasBasePhoneAuth() {
//        // TODO Auto-generated method stub
//
//        PackageManager pm = this.getPackageManager();
//        for (String auth : authBaseArr) {
//            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//    private boolean initDirs() {
//        mSDCardPath = getSdcardDir();
//        if (mSDCardPath == null) {
//            return false;
//        }
//        File f = new File(mSDCardPath, APP_FOLDER_NAME);
//        if (!f.exists()) {
//            try {
//                f.mkdir();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return true;
//    }
//    private void initNavi() {
//        // 申请权限
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//
//            if (!hasBasePhoneAuth()) {
//
//                this.requestPermissions(authBaseArr, authBaseRequestCode);
//                return;
//
//            }
//        }
//
//        BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME,
//                new BaiduNaviManager.NaviInitListener() {
//                    @Override
//                    public void onAuthResult(int status, String msg) {
//                        if (0 == status) {
//                            authinfo = "key校验成功!";
//                        } else {
//                            authinfo = "key校验失败, " + msg;
//                        }
//                        Toast.makeText(BaiduMapGPSActivity.this, authinfo, Toast.LENGTH_LONG).show();
////                        BNDemoMainActivity.this.runOnUiThread(new Runnable() {
////
////                            @Override
////                            public void run() {
////                                Toast.makeText(BNDemoMainActivity.this, authinfo, Toast.LENGTH_LONG).show();
////                            }
////                        });
//                    }
//
//                    public void initSuccess() {
//                        Toast.makeText(BaiduMapGPSActivity.this, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
//                    }
//
//                    public void initStart() {
//                        Toast.makeText(BaiduMapGPSActivity.this, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
//                    }
//
//                    public void initFailed() {
//                        Toast.makeText(BaiduMapGPSActivity.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
//                    }
//                }, null /*mTTSCallback*/);
//    }
//    class MyLocationListenner implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // map view 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null) {
//                return;
//            }
//            mCurrentLat = location.getLatitude();
//            mCurrentLon = location.getLongitude();
//            mCurrentAccracy = location.getRadius();
//            locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentDirection).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
//
//         //   routeplanToNavi(BNRoutePlanNode.CoordinateType.BD09LL);
////            if (isFirstLoc) {
////
////                isFirstLoc = false;
////                LatLng ll = new LatLng(location.getLatitude(),
////                        location.getLongitude());
////                MapStatus.Builder builder = new MapStatus.Builder();
////                builder.target(ll).zoom(18.0f);
////                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
////
////            }
////            Log.i("baidu","-----------"+mCurrentLat+"--------"+mCurrentLon);
//
//        }
//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
//        }
//    }
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initListener() {
//
//    }
//    private boolean hasCompletePhoneAuth() {
//        // TODO Auto-generated method stub
//
//        PackageManager pm = this.getPackageManager();
//        for (String auth : authComArr) {
//            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//    private void routeplanToNavi(BNRoutePlanNode.CoordinateType coType) {
//        mCoordinateType = coType;
//        if (!hasInitSuccess) {
//            Toast.makeText(this, "还未初始化!", Toast.LENGTH_SHORT).show();
//        }
//        // 权限申请
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            // 保证导航功能完备
//            if (!hasCompletePhoneAuth()) {
//                if (!hasRequestComAuth) {
//                    hasRequestComAuth = true;
//                    this.requestPermissions(authComArr, authComRequestCode);
//                    return;
//                } else {
//                    Toast.makeText(this, "没有完备的权限!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        }
//        BNRoutePlanNode sNode = null;
//        BNRoutePlanNode eNode = null;
//        sNode = new BNRoutePlanNode(mCurrentLon,mCurrentLat,"南新路", null, coType);
//        eNode = new BNRoutePlanNode(item.longitude,item.latitude, item.storeName, null, coType);
//        if (sNode != null && eNode != null) {
//            List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
//            list.add(sNode);
//            list.add(eNode);
//            BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));
//
//        }
//    }
//
//    public void toGPS(){
//        //BNDemoMainActivity.activityList.add(this);
//        //createHandler();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//        }
//        View view = null;
//        if (useCommonInterface) {
//            //使用通用接口
//            mBaiduNaviCommonModule = NaviModuleFactory.getNaviModuleManager().getNaviCommonModule(
//                    NaviModuleImpl.BNaviCommonModuleConstants.ROUTE_GUIDE_MODULE, this,
//                    BNaviBaseCallbackModel.BNaviBaseCallbackConstants.CALLBACK_ROUTEGUIDE_TYPE, mOnNavigationListener);
//            if(mBaiduNaviCommonModule != null) {
//                mBaiduNaviCommonModule.onCreate();
//                view = mBaiduNaviCommonModule.getView();
//            }
//
//        } else {
//            //使用传统接口
//            view = BNRouteGuideManager.getInstance().onCreate(this,mOnNavigationListener);
//        }
//
//
//        if (view != null) {
//            setContentView(view);
//        }
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            Bundle bundle = intent.getExtras();
//            if (bundle != null) {
//               // mBNRoutePlanNode = (BNRoutePlanNode) bundle.getSerializable(BNDemoMainActivity.ROUTE_PLAN_NODE);
//
//            }
//        }
//    }
//    private BNRouteGuideManager.OnNavigationListener mOnNavigationListener = new BNRouteGuideManager.OnNavigationListener() {
//
//        @Override
//        public void onNaviGuideEnd() {
//            //退出导航
//            finish();
//        }
//
//        @Override
//        public void notifyOtherAction(int actionType, int arg1, int arg2, Object obj) {
//
//            if (actionType == 0) {
//                //导航到达目的地 自动退出
//                Log.i(TAG, "notifyOtherAction actionType = " + actionType + ",导航到达目的地！");
//            }
//
//            Log.i(TAG, "actionType:" + actionType + "arg1:" + arg1 + "arg2:" + arg2 + "obj:" + obj.toString());
//        }
//
//    };
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(useCommonInterface) {
//            if(mBaiduNaviCommonModule != null) {
//                mBaiduNaviCommonModule.onResume();
//            }
//        } else {
//            BNRouteGuideManager.getInstance().onResume();
//        }
//
//
//    }
//
//
//
//
//
//
//    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {
//
//        private BNRoutePlanNode mBNRoutePlanNode = null;
//
//        public DemoRoutePlanListener(BNRoutePlanNode node) {
//            mBNRoutePlanNode = node;
//        }
//
//        @Override
//        public void onJumpToNavigator() {
//        /*
//         * 设置途径点以及resetEndNode会回调该接口
//         */
//
////            for (Activity ac : activityList) {
////
////                if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {
////
////                    return;
////                }
////            }
////            Intent intent = new Intent(BNDemoMainActivity.this, BNDemoGuideActivity.class);
////            Bundle bundle = new Bundle();
////            bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
////            intent.putExtras(bundle);
////            startActivity(intent);
//            toGPS();
//
//        }
//
//        @Override
//        public void onRoutePlanFailed() {
//            // TODO Auto-generated method stub
//            //Toast.makeText(BNDemoMainActivity.this, "算路失败", Toast.LENGTH_SHORT).show();
//        }
//    }
}
