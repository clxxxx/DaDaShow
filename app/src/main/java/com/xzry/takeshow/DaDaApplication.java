package com.xzry.takeshow;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;
import com.sxjs.common.GlobalAppComponent;
import com.sxjs.common.loadsir.core.LoadSir;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xzry.takeshow.ui.viewcallback.CustomCallback;
import com.xzry.takeshow.ui.viewcallback.EmptyCallback;
import com.xzry.takeshow.ui.viewcallback.ErrorCallback;
import com.xzry.takeshow.ui.viewcallback.LoadingCallback;
import com.xzry.takeshow.ui.viewcallback.TimeoutCallback;

/**
 * Created by 周东阳 on 2017/7/25 0025.
 */

public class DaDaApplication extends MultiDexApplication {

    private static DaDaApplication insatnce;

    @Override
    public void onCreate() {
        super.onCreate();

        insatnce = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        if(true){
            //内存优化
            LeakCanary.install(this);
            //路由器
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        initBaiduMapSDK();      //初始化地图
        ARouter.init(this);
        Fresco.initialize(this);//图片加载


        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();

        GlobalAppComponent.init(this);
    }

    public static Application getInsatnce() {
        return insatnce;
    }

    private void initBaiduMapSDK(){
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(getInsatnce());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        UMShareAPI.get(this);

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);

    }
    {
        PlatformConfig.setWeixin("wxaa803e487961ae06","030d3d0b9f9565555bb0274d6a50e2d1");
        PlatformConfig.setQQZone("","");
        PlatformConfig.setSinaWeibo("","","");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
    }



}