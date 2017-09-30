package com.xzry.takeshow.widget;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xzry.takeshow.R;

/**
 * @author: luosy
 * @date: 2017-8-15
 * @description:
 */


public class MessageDialog extends DialogFragment{
//        final AlertDialog dialog = new AlertDialog.Builder(this).create();
//        dialog.show();
//        dialog.getWindow().setContentView(R.layout.dialog_style);
//        TextView tv_goto = (TextView) dialog.findViewById(R.id.dialog_goto);
//        TextView tv_jump = (TextView) dialog.findViewById(R.id.dialog_jump);
//        tv_goto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toActivity(RegistSetActivity.class);
//            }
//        });
//        tv_jump.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toActivity(LoginActivity.class);
//                dialog.dismiss();
//            }
//        });


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.item_exit_app);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER; //
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        TextView tv_confirm = (TextView) dialog.findViewById(R.id.exit_confirm);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.exit_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        return dialog;
    }
}
