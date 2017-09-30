package com.xzry.takeshow.ui.login;

import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.CountDownTimerUtil;
import com.sxjs.common.util.StatusBarUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseConfig;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.MessageEvent;
import com.xzry.takeshow.entity.UserInfo;
import com.xzry.takeshow.entity.request.RequestLogin;
import com.xzry.takeshow.utils.PopupWindowUtil;
import com.xzry.takeshow.widget.CodeFrame;
import com.xzry.takeshow.widget.PhoneEditText;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;

import static com.xzry.takeshow.R.id.bin_mob_close;

/**
 *  绑定手机号
 */
public class BindMobileActivity extends BaseActivity implements View.OnClickListener,TextWatcher,LoginContract.View{
    @BindView(R.id.code_frame)
    CodeFrame codeFrame;
    @BindView(bin_mob_close)
    ImageView iv_close;
    @BindView(R.id.bin_mob_headimg)
    ExpandImageView et_headImg;

    @BindView(R.id.bin_mob_mobile)
    PhoneEditText et_getAccount;
    @BindView(R.id.bin_mob_clear)
    ImageView iv_clear;
    @BindView(R.id.bin_mob_getcode)
    TextView tv_getCode;
    @BindView(R.id.bin_mob_join)
    TextView tv_join;
    @BindView(R.id.bin_mob_nickname)
    TextView tv_nickname;
    @Inject
    LoginPresenter mPresenter;
    private String uid;
    private String name;
    private String gender;
    private String iconurl;
    CountDownTimerUtil timer;
    @Override
    public int getLayout() {
        return R.layout.activity_bind_mobile;
    }

    @Override
    protected void initView() {
        DaggerLoginComponent.builder()
                .appComponent(getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        init();
    }

    @Override
    protected void initListener() {
        tv_getCode.setOnClickListener(this);
        tv_join.setOnClickListener(this);
        iv_clear.setOnClickListener(this);
        et_getAccount.addTextChangedListener(this);
        iv_close.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bin_mob_close:
                showExit();
                break;
            case R.id.bin_mob_getcode:
                toGetCode();
                break;
            case R.id.bin_mob_clear:

                et_getAccount.setText("");
                break;
            case R.id.bin_mob_join:
                toLogin();
                break;
            default:
                break;
        }
    }
    public void init(){
        StatusBarUtil.transparencyBar(this);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        iconurl = intent.getStringExtra("iconurl");
        et_headImg.setImageURI(iconurl);
        tv_nickname.setText(name);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (getAccount().length() > 0) {
            iv_clear.setVisibility(View.VISIBLE);
        }else {
            iv_clear.setVisibility(View.GONE);
        }
    }
    public void toGetCode(){
        checkOutMoblie(getAccount());
        tv_getCode.setFocusable(false);
        mPresenter.getCode(getAccount());
    }
    private void showExit(){
        View rootView = findViewById(R.layout.activity_setting);
        View view = this.getLayoutInflater().inflate(R.layout.item_exit_app, null);
        final PopupWindowUtil pop = new PopupWindowUtil();
        pop.showCenter(getWindow(),rootView,view);
        TextView tv_confirm = (TextView) view.findViewById(R.id.exit_confirm);
        TextView tv_cancle = (TextView) view.findViewById(R.id.exit_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("error"));
                pop.dissPopWin();
                finish();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dissPopWin();
            }
        });
    }

    @Override
    public void showLoginResult(UserInfo user) {
        if (user == null) {
            showShortToast("登录失败");
            return;
        }
        BaseValue.ISLOGIN = true;
        BaseValue.USER_ID = user.id;
        BaseValue.TOKEN = user.code;
        EventBus.getDefault().post(new MessageEvent(BaseValue.LOGIN));
        showShortToast("登录成功");
        finish();
    }

    @Override
    public void showLoginError() {
        showShortToast("登录失败");
    }

    //短信发送成功
    @Override
    public void showToast(int state) {
        if (state == 1) {
            showShortToast("已发送");
            //开始倒计时
            startCountdown();
        }else{
            tv_getCode.setFocusable(true);
            showShortToast("发送失败");
        }
    }
    public void startCountdown() {
        timer = new CountDownTimerUtil(BaseConfig.total_time, BaseConfig.interval_time) {

            @Override
            public void onTick(long millisUntilFinished) {
                tv_getCode.setText(millisUntilFinished / 1000 + "秒后重发");
            }

            @Override
            public void onFinish() {
                tv_getCode.setEnabled(true);
                tv_getCode.setText("获取验证码");

            }
        };
        timer.start();
    }

    public void toLogin(){
        checkOutMoblie(getAccount());
        checkOutCode();
        RequestLogin login = new RequestLogin();
        login.setMobile(getAccount());
        login.setLoginType("200");
        login.setVerifyCode(getCode());
        login.setDeviceNumber(getIMEI());
        login.setSourceAppid(uid);
        login.setSex(gender);
        login.setHeaderUrl(iconurl);
        mPresenter.toLogin(login);
    }
    public void checkOutMoblie(String Moblie) {
        if (TextUtils.isEmpty(Moblie)) {
            showShortToast("手机号码不能为空");
            return;
        }
        if (!Moblie.matches("^1[0-9]{10}$")) {
            showShortToast("请输入正确的手机号！");
            return;
        }

    }
    public void checkOutCode(){
        if (TextUtils.isEmpty(getCode())) {
            showShortToast("验证码不能为空");
            return;
        }
        if (!getCode().matches("[0-9]{6}")) {
            showShortToast("请输入正确的验证码");
            return;
        }
    }
    public String getAccount(){
        return et_getAccount.getPhoneText();
    }
    public String getCode(){
        return codeFrame.getCode();
    }
    public String getIMEI(){
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            finish();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null) {
            timer.cancel();
        }

    }
}
