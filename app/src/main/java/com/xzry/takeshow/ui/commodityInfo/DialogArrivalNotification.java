package com.xzry.takeshow.ui.commodityInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxjs.common.widget.dialog.BaseDialog;
import com.xzry.takeshow.R;

/**
 * Created by 周东阳 on 2017/9/22 0022.
 */

public class DialogArrivalNotification extends BaseDialog {

    private TextView cancel;
    private TextView confirm;
    private ImageView close;
    private EditText phone;

    public DialogArrivalNotification(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public DialogArrivalNotification(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public DialogArrivalNotification(Context context) {
        super(context);
        initView();
    }

    public DialogArrivalNotification(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    private void initView() {
        View dialog_view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_notification, null);
        cancel = (TextView)  dialog_view.findViewById(R.id.cancel);
        confirm = (TextView)  dialog_view.findViewById(R.id.confirm);
        close = (ImageView)  dialog_view.findViewById(R.id.close);
        phone = (EditText)  dialog_view.findViewById(R.id.phone);
        setContentView(dialog_view);

    }


    public TextView getCancel() {
        return cancel;
    }

    public TextView getConfirm() {
        return confirm;
    }

    public ImageView getClose() {
        return close;
    }

    public EditText getPhone() {
        return phone;
    }
}
