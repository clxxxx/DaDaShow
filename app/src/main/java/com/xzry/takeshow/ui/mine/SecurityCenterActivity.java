package com.xzry.takeshow.ui.mine;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 *  安全中心
 */
public class SecurityCenterActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.security_center_account)
    RelativeLayout lay_binddingAccount;
    @BindView(R.id.security_center_account_text)
    TextView tv_binddingAccount;
    @Override
    public int getLayout() {
        return R.layout.activity_security_center;
    }

    @Override
    protected void initView() {
        titleBarView.setTitle("安全中心");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        lay_binddingAccount.setOnClickListener(this);
        titleBarView.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == lay_binddingAccount) {
            toActivity(PhoneEditActivity.class);
        }
    }
}
