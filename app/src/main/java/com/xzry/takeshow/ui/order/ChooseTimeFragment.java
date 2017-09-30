package com.xzry.takeshow.ui.order;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxjs.common.util.DateUtils;
import com.xzry.takeshow.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

/**
 * Created by 周东阳 on 2017/9/12 0012.
 */

public class ChooseTimeFragment extends DialogFragment implements View.OnClickListener{

    private Context mContext;
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private TextView time4;
    private TextView time5;
    private TextView time6;
    private TextView time7;

    public ChooseTimeFragment(Context mContext){
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_choosetime);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        ImageView close = (ImageView) dialog.findViewById(R.id.close);

        time1 = (TextView) dialog.findViewById(R.id.time1);
        time1.setText(DateUtils.convertDate2String("MM月dd日") + "   (" + DateUtils.week() + ")");
        time2 = (TextView) dialog.findViewById(R.id.time2);
        time2.setText(DateUtils.convertDate2String(DateUtils.add(Calendar.DATE, 1), "MM月dd日") + "   (" + DateUtils.week(1) + ")");
        time3 = (TextView) dialog.findViewById(R.id.time3);
        time3.setText(DateUtils.convertDate2String(DateUtils.add(Calendar.DATE, 2), "MM月dd日") + "   (" + DateUtils.week(2) + ")");
        time4 = (TextView) dialog.findViewById(R.id.time4);
        time4.setText(DateUtils.convertDate2String(DateUtils.add(Calendar.DATE, 3), "MM月dd日") + "   (" + DateUtils.week(3) + ")");
        time5 = (TextView) dialog.findViewById(R.id.time5);
        time5.setText(DateUtils.convertDate2String(DateUtils.add(Calendar.DATE, 4), "MM月dd日") + "   (" + DateUtils.week(4) + ")");
        time6 = (TextView) dialog.findViewById(R.id.time6);
        time6.setText(DateUtils.convertDate2String(DateUtils.add(Calendar.DATE, 5), "MM月dd日") + "   (" + DateUtils.week(5) + ")");
        time7 = (TextView) dialog.findViewById(R.id.time7);
        time7.setText(DateUtils.convertDate2String(DateUtils.add(Calendar.DATE, 6), "MM月dd日") + "   (" + DateUtils.week(6) + ")");

        close.setOnClickListener(this);
        time1.setOnClickListener(this);
        time2.setOnClickListener(this);
        time3.setOnClickListener(this);
        time4.setOnClickListener(this);
        time5.setOnClickListener(this);
        time6.setOnClickListener(this);
        time7.setOnClickListener(this);

        return dialog;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                dismiss();
                break;
            case R.id.time1:
                EventBus.getDefault().post(DateUtils.year() + "年" + time1.getText());
                dismiss();
                break;
            case R.id.time2:
                EventBus.getDefault().post(DateUtils.year() + "年" + time2.getText());
                dismiss();
                break;
            case R.id.time3:
                EventBus.getDefault().post(DateUtils.year() + "年" + time3.getText());
                dismiss();
                break;
            case R.id.time4:
                EventBus.getDefault().post(DateUtils.year() + "年" + time4.getText());
                dismiss();
                break;
            case R.id.time5:
                EventBus.getDefault().post(DateUtils.year() + "年" + time5.getText());
                dismiss();
                break;
            case R.id.time6:
                EventBus.getDefault().post(DateUtils.year() + "年" + time6.getText());
                dismiss();
                break;
            case R.id.time7:
                EventBus.getDefault().post(DateUtils.year() + "年" + time7.getText());
                dismiss();
                break;
        }
    }
}
