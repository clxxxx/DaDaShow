package com.xzry.takeshow.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;

public class SplashActivity extends BaseActivity {

    private static final int BAIDU_READ_PHONE_STATE =100;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT>=23){
            showContacts();
        }else{
            jionMain();
        }
    }

    @Override
    protected void initData() {
        getStatusHeight();
    }

    @Override
    protected void initListener() {

    }

    /**
     *  获取状态栏高度
     */
    public void getStatusHeight(){
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        //根据资源ID获取响应的尺寸值
        final int statusBarHeight = resourceId > 0 ? getResources().getDimensionPixelSize(resourceId) : 50;
        BaseValue.STATUS_HEIGHT = statusBarHeight;
        WindowManager windowManager = getWindowManager();
        BaseValue.WINDOW_WIDTH  = windowManager.getDefaultDisplay().getWidth();
        BaseValue.WINDOW_HEIGHT  = windowManager.getDefaultDisplay().getHeight();
    }
    /**
     * 获取屏幕的宽和高
     */
    public void getScreenPixels() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

    }
    public void showContacts(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) //通话权限
                != PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(SplashActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        }else{
            jionMain();
        }
    }


    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    jionMain();
                } else {
                    // 没有获取到权限，做特殊处理
                    final DialogCompetenceHint dialogCompetenceHint = new DialogCompetenceHint(this);
                    dialogCompetenceHint.getTvSure().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogCompetenceHint.cancel();
                            ActivityCompat.requestPermissions(SplashActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
                        }
                    });
                    dialogCompetenceHint.show();
                }
                break;
            default:
                break;
        }
    }

    public void jionMain(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
