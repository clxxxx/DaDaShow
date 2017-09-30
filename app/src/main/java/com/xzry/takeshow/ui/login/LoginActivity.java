package com.xzry.takeshow.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.CountDownTimerUtil;
import com.xzry.takeshow.entity.MessageEvent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseConfig;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.UserInfo;
import com.xzry.takeshow.entity.request.RequestLogin;
import com.xzry.takeshow.widget.CodeFrame;
import com.xzry.takeshow.widget.PhoneEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;




public class LoginActivity extends BaseActivity implements LoginContract.View,View.OnClickListener,TextWatcher{
    @BindView(R.id.login_close)
    ImageView iv_close;


    @BindView(R.id.code_frame)
    CodeFrame codeFrame;
    @BindView(R.id.login_account)
    PhoneEditText et_getAccount;
    @BindView(R.id.account_clear    )
    ImageView iv_clear;
    @BindView(R.id.login_getcode)
    TextView tv_getCode;
    @BindView(R.id.login_join)
    TextView tv_join;
    @BindView(R.id.login_threed)
    ImageView iv_threedLogin;

    @Inject
    LoginPresenter mPresenter;
    ProgressDialog dialog;
    CountDownTimerUtil timer;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        DaggerLoginComponent.builder()
                .appComponent(getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
        dialog = new ProgressDialog(this);
//        et_code.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {
//            @Override
//            public void onInputFinish(String password) {
//                Toast.makeText(context,"输入结束" + et_code.getText().toString(),Toast.LENGTH_SHORT).show();
//                et_code.setText(null);
//            }
//        });
//        et_code.initStyle(R.drawable.login_code, 6, 0.3f, R.color.text_color_2, R.color.text_color_2, 20);
//        et_code.setShowPwd(false);
//        et_code.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
//            @Override
//            public void onFinish(String str) {//密码输入完后的回调
//                //Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
//                if (str.length() == 6) {
//                    tv_join.setEnabled(true);
//                    tv_join.setBackgroundResource(R.color.theme_dark_color);
//                }else {
//                    tv_join.setEnabled(false);
//                    tv_join.setBackgroundResource(R.color.text_color_1);
//                }
//            }
//        });

    }

    @Override
    protected void initData() {
        //UmengTool.checkWx(this);
        //UM设置每次登录都显示授权页
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
    }

    @Override
    protected void initListener() {
        tv_getCode.setOnClickListener(this);
        tv_join.setOnClickListener(this);
        iv_clear.setOnClickListener(this);
        et_getAccount.addTextChangedListener(this);
        iv_threedLogin.setOnClickListener(this);
        iv_close.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_close:
                EventBus.getDefault().post(new MessageEvent("error"));
                finish();
                break;
            case R.id.login_getcode:
                toGetCode();
                break;
            case R.id.account_clear:
                et_getAccount.setText("");
                break;
            case R.id.login_threed:
                toLoginThreed();
                break;
            case R.id.login_join:
                toLogin();
                break;

            default:
                break;
        }
    }

    private void shared(){
//        new ShareAction(MainActivity.this)
//                .setPlatform(SHARE_MEDIA.QQ)//传入平台
//                .withText("hello")//分享内容
//                .setCallback(umShareListener)//回调监听器
//                .share();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    public void toGetCode(){

        tv_getCode.setClickable(false);
        if (getAccount().length()<=0) {
            showShortToast("手机号码不能为空");
            tv_getCode.setClickable(true);
            return;
        }
        if (!getAccount().matches("^1[0-9]{10}$")) {
            showShortToast("请输入正确的手机号！");
            tv_getCode.setClickable(true);
            return;
        }
        mPresenter.getCode(getAccount());
    }
    public void toLogin(){
        if (getAccount().length()<=0) {
            showShortToast("手机号码不能为空");

            return;
        }
        if (!getAccount().matches("^1[0-9]{10}$")) {
            showShortToast("请输入正确的手机号！");

            return;
        }
        if (TextUtils.isEmpty(getCode())) {
            showShortToast("验证码不能为空");
            return;
        }
        if (!getCode().matches("[0-9]{6}")) {
            showShortToast("请输入正确的验证码");
            return;
        }
        RequestLogin login = new RequestLogin();
        login.setMobile(getAccount());
        login.setLoginType("300");
        login.setVerifyCode(getCode());
        login.setDeviceNumber(getIMEI());
        Log.i("login",""+getAccount()+"---"+getCode()+"---"+getIMEI());
        mPresenter.toLogin(login);
    }
    public void toLoginThreed(){
//        final boolean isauth = UMShareAPI.get(this).isAuthorize(this, SHARE_MEDIA.WEIXIN);
//        if (isauth) {
//            UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, authListener);
//        } else {
//            UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
//        }
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);

    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.i("aa","------------开始-----------");
            //授权开始的回调
            SocializeUtils.safeCloseDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            Intent intent = new Intent(LoginActivity.this,BindMobileActivity.class);
            intent.putExtra("uid",data.get("uid"));
            intent.putExtra("name",data.get("name"));
            intent.putExtra("gender",data.get("gender"));
            intent.putExtra("iconurl",data.get("iconurl"));
            startActivity(intent);
            iv_threedLogin.setFocusable(true);


        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            showShortToast("授权失败"+t);
            iv_threedLogin.setFocusable(true);
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            iv_threedLogin.setFocusable(true);
            showShortToast("取消了");
        }
    };


    @Override
    public void showLoginResult(UserInfo user) {
        if (user == null) {
            showShortToast("登录失败");
            return;
        }
        BaseValue.ISLOGIN = true;
        BaseValue.USER_ID = user.id;
        BaseValue.TOKEN = user.code;
        Log.i("aa","---------toekn-------------"+user.code);
        EventBus.getDefault().post(new MessageEvent("success"));
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
            tv_getCode.setClickable(true);
            showShortToast("发送失败");
        }
    }
    public void startCountdown() {
        timer = new CountDownTimerUtil(BaseConfig.total_time, BaseConfig.interval_time) {

            @Override
            public void onTick(long millisUntilFinished) {
                tv_getCode.setText(millisUntilFinished / 1000+"秒后重发");
            }
            @Override
            public void onFinish() {
                tv_getCode.setClickable(true);
                tv_getCode.setText("获取验证码");

            }
        };
        timer.start();
    }



    public String getAccount(){
        return et_getAccount.getPhoneText().trim();
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

        UMShareAPI.get(this).release();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }


}
