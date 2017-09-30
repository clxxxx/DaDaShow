package com.xzry.takeshow.ui.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.widget.dialog.BaseDialog;
import com.xzry.takeshow.R;

/**
 * Created by 周东阳 on 2017/9/29 0029.
 */

public class DialogDelete extends BaseDialog {

    private TextView delete;

    public DialogDelete(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public DialogDelete(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public DialogDelete(Context context) {
        super(context);
        initView();
    }

    public DialogDelete(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    private void initView() {
        View dialog_view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_shoppingcar_delete, null);
        delete = (TextView) dialog_view.findViewById(R.id.delete);
        setContentView(dialog_view);
    }

    public TextView getDelete() {
        return delete;
    }
}