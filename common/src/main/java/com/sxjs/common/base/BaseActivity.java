package com.sxjs.common.base;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.sxjs.common.AppComponent;
import com.sxjs.common.GlobalAppComponent;
import com.sxjs.common.model.DataManager;
import com.sxjs.common.util.DialogUtil;
import com.sxjs.common.util.StatusBarUtil;
import com.sxjs.common.util.statusBar.Eyes;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/3/12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    InputMethodManager imManager;
    protected int screenWidth;
    protected int screenHigh;
    protected DataManager mDataManager;
    protected Context mContext;
    protected Dialog loadingDialog;
    protected Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(View.inflate(this, getLayout(), null));
        unbinder = ButterKnife.bind(this);
        imManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        Eyes.setStatusBarLightMode(this, Color.WHITE, true);//true为是否设置小米状态栏为，如果是则将状态栏文字改为黑色

        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        screenHigh = dpMetrics.heightPixels;

        initView();
        initData();
        initListener();
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
    }

    protected AppComponent getAppComponent() {
        return GlobalAppComponent.getAppComponent();
    }

    protected void addFragment(int containerViewId, Fragment fragment , String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(containerViewId, fragment , tag);
        fragmentTransaction.commit();
    }

    /**
     * 点击屏幕空白处隐藏键盘
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                imManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 跳转页面
     */
    public void toActivity( Class clss){
        Intent intent=new Intent(this,clss);
        startActivity(intent);
    }

    public void showShortToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(message, Toast.LENGTH_SHORT);
            }
        });
    }

    public void showLongToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(message, Toast.LENGTH_LONG);
            }
        });
    }

    private void showToast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }


    //获取布局文件
    public abstract int getLayout();
    //初始化控件
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();
    //初始化监听
    protected abstract void initListener();

    protected void showProgressDialog(){
        this.showProgressDialog(null,null);
    }

    protected void showProgressDialog(String msg){
        this.showProgressDialog(msg , null);
    }

    protected void showProgressDialog(DialogInterface.OnCancelListener listener){
        this.showProgressDialog(null ,listener);
    }

    protected void showProgressDialog(String msg , DialogInterface.OnCancelListener listener){
        if(loadingDialog == null){
            loadingDialog = DialogUtil.createLoadingDialog(this, msg, listener);
        }else if(!loadingDialog.isShowing()){
            loadingDialog.show();
        }

    }

    protected void hiddenProgressDialog(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
            loadingDialog = null;
        }

        if(unbinder != null){
            unbinder.unbind();
        }
    }



}
