package com.xzry.takeshow.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sxjs.common.widget.dialog.BaseDialog;
import com.xzry.takeshow.R;

/**
 * Created by 周东阳 on 2017/9/15 0015.
 */

public class DialogCompetenceHint extends BaseDialog {

    private TextView mTvSure;

    public DialogCompetenceHint(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public DialogCompetenceHint(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public DialogCompetenceHint(Context context) {
        super(context);
        initView();
    }

    public DialogCompetenceHint(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    private void initView() {
        View dialog_view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_competence_hint, null);
        mTvSure = (TextView) dialog_view.findViewById(R.id.tv_sure);
        setContentView(dialog_view);
    }

    public TextView getTvSure() {
        return mTvSure;
    }
}
