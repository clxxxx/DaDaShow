package com.xzry.takeshow.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;
import com.sxjs.common.util.LogUtil;
import com.xzry.takeshow.DaDaApplication;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.utils.SpUtil;

import static com.xzry.takeshow.base.BaseValue.CURRENT_POSITION;
import static com.xzry.takeshow.base.BaseValue.CURRENT_POSITION_NOFITY;

/**
 * Created by 周东阳 on 2017/9/16 0016.
 */

public class BaiduService extends Service {
    private static final String TAG = "BaiduService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /**
         * 百度定位
         */
        BaiduPresenter.getInstance().initLocation(getApplicationContext(), new BDLocationListener() {
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
                Gson gson = new Gson();
                String jsonObject = gson.toJson(bdLocation);
                BaseValue.latitude = bdLocation.getLatitude()+"";
                BaseValue.longitude = bdLocation.getLongitude()+"";
                SpUtil.getInstance().putString(CURRENT_POSITION, jsonObject);
                DaDaApplication.getInsatnce().sendBroadcast(new Intent(CURRENT_POSITION_NOFITY));
                stopSelf();
            }


        });
        BaiduPresenter.getInstance().getLocationClient(getApplicationContext()).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        BaiduPresenter.getInstance().getLocationClient(getApplicationContext()).stop();
        super.onDestroy();
    }
}

