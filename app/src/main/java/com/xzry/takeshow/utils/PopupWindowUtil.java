package com.xzry.takeshow.utils;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.xzry.takeshow.base.BaseValue;

/**
 * @author: luosy
 * @date: 2017-9-2
 * @description:
 */


public class PopupWindowUtil {
    public Window window;
    public View rootView;
    public View view;
    public PopupWindow popupWindow;

    public PopupWindowUtil() {

    }
    public void showCenter(Window window, View rootView, View view){
        this.window = window;
        this.rootView = rootView;
        this.view = view;
        popupWindow = new PopupWindow(rootView,(int)(BaseValue.WINDOW_WIDTH*0.8),(int)(BaseValue.WINDOW_HEIGHT*0.5));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //popupWindow弹出后屏幕半透明
        BackgroudAlpha((float)0.5);
        //弹出窗口关闭事件
        popupWindow.setOnDismissListener(new popupwindowdismisslistener());
    }

    public void showMarginTop(Window window, View rootView, View view,int y){
        this.window = window;
        this.rootView = rootView;
        this.view = view;
        popupWindow = new PopupWindow(rootView, ViewPager.LayoutParams.MATCH_PARENT,ViewPager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.TOP, 0, y);
        //popupWindow弹出后屏幕半透明
        //BackgroudAlpha((float)0.5);
        //弹出窗口关闭事件
        popupWindow.setOnDismissListener(new popupwindowdismisslistener());
    }
    public void showMarginFullTransTop(Window window, View rootView, View view,int y){
        this.window = window;
        this.rootView = rootView;
        this.view = view;
        popupWindow = new PopupWindow(rootView, ViewPager.LayoutParams.MATCH_PARENT,ViewPager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.TOP, 0, y);
        //popupWindow弹出后屏幕半透明
        //BackgroudAlpha((float)0.8);
        //弹出窗口关闭事件
        popupWindow.setOnDismissListener(new popupwindowdismisslistener());
    }
    public void showBottom(Window window, View rootView, View view){
        this.window = window;
        this.rootView = rootView;
        this.view = view;
        popupWindow = new PopupWindow(rootView, ViewPager.LayoutParams.MATCH_PARENT,ViewPager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //popupWindow弹出后屏幕半透明
        //BackgroudAlpha((float)0.8);
        //popupWindow.update();
        //弹出窗口关闭事件
        popupWindow.setOnDismissListener(new popupwindowdismisslistener());


    }
//    // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
//    PopupWindow window = new PopupWindow(popupView, 400, 600);
//    // TODO: 2016/5/17 设置动画
//                window.setAnimationStyle(R.style.popup_window_anim);
//    // TODO: 2016/5/17 设置背景颜色
//                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
//    // TODO: 2016/5/17 设置可以获取焦点
//                window.setFocusable(true);
//    // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
//                window.setOutsideTouchable(true);
//    // TODO：更新popupwindow的状态
//                window.update();
//    // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
//                window.showAsDropDown(btnPopup, 0, 20);



    public void dissPopWin(){
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }
    //设置屏幕背景透明度
    public void BackgroudAlpha(float alpha) {

        WindowManager.LayoutParams l = window.getAttributes();
        l.alpha = alpha;
        window.setAttributes(l);
    }
    //点击其他部分popwindow消失时，屏幕恢复透明度
    class popupwindowdismisslistener implements PopupWindow.OnDismissListener{
        @Override
        public void onDismiss() {
            BackgroudAlpha((float)1);
        }

    }
}
