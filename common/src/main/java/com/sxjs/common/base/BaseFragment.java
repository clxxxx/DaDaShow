package com.sxjs.common.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sxjs.common.AppComponent;
import com.sxjs.common.GlobalAppComponent;
import com.sxjs.common.util.DialogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/3/15.
 */

public abstract class BaseFragment extends Fragment{
    protected Activity mActivity;
    protected Unbinder unbinder;
    protected Context mContext;
    /**
     * gif_logo进度dialog
     */
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);
        ButterKnife.bind(this,view);//绑定framgent
        initView();
        initData();
        initListener();
        return view;
    }
    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        //隐藏状态栏
        //StatusBarCompat.translucentStatusBar(getActivity());
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mContext = getAppComponent().getContext();
    }
    /**
     * 跳转页面
     */
    public void toActivity( Class clss){
        Intent intent=new Intent(getActivity(),clss);
        startActivity(intent);
    }
    protected void showShortToast(String message){
        Toast.makeText(mActivity.getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message){
        Toast.makeText(mActivity.getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    protected abstract int getLayout();
    //初始化控件
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();
    //初始化监听
    protected abstract void initListener();

    protected AppComponent getAppComponent() {
        return GlobalAppComponent.getAppComponent();
    }

    protected void showJDLoadingDialog(){
        if(dialog == null)dialog = DialogUtil.createJDLoadingDialog(mActivity, null);
        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    protected void hideJDLoadingDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null){
            unbinder.unbind();
        }
        if(dialog != null){
            if(dialog.isShowing())dialog.dismiss();
            dialog = null;
        }
    }
}
