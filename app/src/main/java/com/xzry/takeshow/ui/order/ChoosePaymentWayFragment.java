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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.xzry.takeshow.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 周东阳 on 2017/9/13 0013.
 */

public class ChoosePaymentWayFragment extends DialogFragment implements View.OnClickListener{

    private Context mContext;
    private String money;
    private String payment = "1";
    private LinearLayout payment_alipay;
    private LinearLayout payment_weixin;

    private ClickLisenter onClickListener;

    public ChoosePaymentWayFragment(Context mContext, String money, ClickLisenter onClickListener){
        this.mContext = mContext;
        this.money = money;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_choose_payment);
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
        payment_alipay = (LinearLayout) dialog.findViewById(R.id.payment_alipay);
        payment_weixin = (LinearLayout) dialog.findViewById(R.id.payment_weixin);
        TextView money_tv = (TextView) dialog.findViewById(R.id.money_tv);
        TextView affirm = (TextView) dialog.findViewById(R.id.affirm);
        money_tv.setText(money);

        close.setOnClickListener(this);
        payment_alipay.setOnClickListener(this);
        payment_weixin.setOnClickListener(this);
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onPayClickListener(payment);
            }
        });
        payment_weixin.setSelected(true);

        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                dismiss();
                break;
            case R.id.payment_alipay:
                payment_weixin.setSelected(false);
                payment_alipay.setSelected(true);
                payment = "2";
                break;
            case R.id.payment_weixin:
                payment_weixin.setSelected(true);
                payment_alipay.setSelected(false);
                payment = "1";
                break;
        }
    }


    public interface ClickLisenter{
        void onPayClickListener(String payment);
    }
}
