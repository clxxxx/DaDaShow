package com.xzry.takeshow.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.MessageEvent;
import com.xzry.takeshow.entity.UserInfo;
import com.xzry.takeshow.entity.request.RequestLogin;
import com.xzry.takeshow.service.BaiduService;
import com.xzry.takeshow.ui.fashionworld.FashionWorldFragment;
import com.xzry.takeshow.ui.fittingroom.FittingRoomFragment;
import com.xzry.takeshow.ui.homepager.HomeFragment;
import com.xzry.takeshow.ui.login.LoginActivity;
import com.xzry.takeshow.ui.mine.MineFragment;
import com.xzry.takeshow.ui.shoppingcart.ShoppingCartFragment;
import com.xzry.takeshow.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View, View.OnClickListener{

    @Inject
    MainPresenter presenter;

    @BindView(R.id.main_container)
    FrameLayout mainContainer;

    @BindView(R.id.main_button_btn1)
    LinearLayout btn_homePage;              //首页
    @BindView(R.id.home_btn_img)
    ImageView home_btn_img;
    @BindView(R.id.main_button_btn2)
    LinearLayout btn_fashionCircles;     //时尚圈
    @BindView(R.id.main_button_btn3)
    LinearLayout btn_fittingRoom;                  //定制模块
    @BindView(R.id.main_button_btn4)
    LinearLayout btn_shopCar;            //购物车
    @BindView(R.id.main_button_btn5)
    LinearLayout btn_mine;               //我的

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private FashionWorldFragment mfashionWorldFragment;
    private FittingRoomFragment fittingRoomFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MineFragment mMineFragment;
    private Fragment[] fragments;
    // 当前显示fragment
    private int currentTabIndex = 0;
    private int position = 0;
    private LinearLayout[] mTabs;
    public static void startActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {


        mFragmentManager = getSupportFragmentManager();

        mHomeFragment = (HomeFragment) mFragmentManager.findFragmentByTag("home_fg");
        mfashionWorldFragment = (FashionWorldFragment) mFragmentManager.findFragmentByTag("fashionWorld_fg");
        fittingRoomFragment = (FittingRoomFragment) mFragmentManager.findFragmentByTag("fittingroom_fg");
        shoppingCartFragment = (ShoppingCartFragment) mFragmentManager.findFragmentByTag("shoppingcart_fg");
        mMineFragment = (MineFragment) mFragmentManager.findFragmentByTag("mine_fg");
        if(mHomeFragment == null){
            mHomeFragment = HomeFragment.newInstance();
            addFragment(R.id.main_container, mHomeFragment, "home_fg");
        }
        if(mfashionWorldFragment == null){
            mfashionWorldFragment = FashionWorldFragment.newInstance();
            addFragment(R.id.main_container, mfashionWorldFragment, "fashionWorld_fg");
        }
        if (fittingRoomFragment == null) {
            fittingRoomFragment = FittingRoomFragment.newInstance();
            addFragment(R.id.main_container,fittingRoomFragment,"fittingroom_fg");
        }
        if (shoppingCartFragment == null) {
            shoppingCartFragment = ShoppingCartFragment.newInstance();
            addFragment(R.id.main_container,shoppingCartFragment,"shoppingcart_fg");
        }
        if (mMineFragment == null) {
            mMineFragment = MineFragment.newInstance();
            addFragment(R.id.main_container,mMineFragment,"mine_fg");
        }
        fragments = new Fragment[]{mHomeFragment, mfashionWorldFragment,fittingRoomFragment,shoppingCartFragment,mMineFragment};

        mFragmentManager.beginTransaction().show(mHomeFragment).hide(mfashionWorldFragment).hide(fittingRoomFragment).hide(shoppingCartFragment).hide(mMineFragment).commitAllowingStateLoss();


        mTabs = new LinearLayout[5];
        mTabs[0] = btn_homePage;
        mTabs[1] = btn_fashionCircles;
        mTabs[2] = btn_fittingRoom;
        mTabs[3] = btn_shopCar;
        mTabs[4] = btn_mine;

        mTabs[0].setSelected(true);

        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void initData() {
        init();
    }

    @Override
    protected void initListener() {
        btn_homePage.setOnClickListener(this);
        btn_fashionCircles.setOnClickListener(this);
        btn_shopCar.setOnClickListener(this);
        btn_mine.setOnClickListener(this);
        btn_fittingRoom.setOnClickListener(this);
    }

    private void init(){
        EventBus.getDefault().register(this);
        BaseValue.USER_ID = getUserID();
        if (TextUtils.equals(getToken(),"")) {
            BaseValue.ISLOGIN = false;
        }else {
            RequestLogin login = new RequestLogin();
            login.setLoginType("100");
            login.setCode(getToken());
            login.setDeviceNumber(getIMEI());
            presenter.toTokenLogin(login);
        }
    }
    public String getToken(){
        return SpUtil.getInstance().getString(BaseValue.User.TOKEN,"");
    }
    public String getIMEI(){
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }

    public String getUserID(){
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        if (messageEvent.getMessage().equals("success")) {
            if (position == 3) {
                shoppingCartFragment.getShoppingCar();
            }else if (position == 4) {
                mMineFragment.getMineData();
            }
            currentTabIndex();

        }else if (messageEvent.getMessage().equals("error")){
            position = currentTabIndex;
        }
    }

    //设置当前显示的fragment
    private void currentTabIndex(){

        if (currentTabIndex != position) {
            mTabs[currentTabIndex].setSelected(false);
            mFragmentManager.beginTransaction().hide(fragments[currentTabIndex]).show(fragments[position])
                    .commitAllowingStateLoss();
            mTabs[position].setSelected(true);
            currentTabIndex = position;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_button_btn1:
                position = 0;
                currentTabIndex();
                break;
            case R.id.main_button_btn2://时尚圈
                position = 1;
                currentTabIndex();
                break;
            case R.id.main_button_btn3://定制
                position = 2;
                currentTabIndex();
                break;
            case R.id.main_button_btn4://购物车
                if (position == 3)
                    return;
                position = 3;
                if (BaseValue.ISLOGIN) {

                    shoppingCartFragment.getShoppingCar();
                    currentTabIndex();
                }else {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class),1);
                }

                break;
            case R.id.main_button_btn5://我的
                if (position == 4) {
                    return;
                }
                position = 4;
                if (BaseValue.ISLOGIN) {
                    mMineFragment.getMineData();
                    currentTabIndex();
                }else {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class),1);
                }
                break;
            default:
                break;

        }
    }


    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startService(new Intent(this, BaiduService.class));
                } else {
                    showShortToast("需到手机设置中开启搭搭秀的位置问权限");
                }
                break;
        }
    }

    @Override
    public void loginResult(UserInfo user) {
        if (user == null) {
            BaseValue.ISLOGIN = false;
            return;
        }
        BaseValue.ISLOGIN = true;
        BaseValue.TOKEN = user.code;
        BaseValue.USER_ID = user.id;
        SpUtil.getInstance().putString(BaseValue.User.TOKEN, user.code);
        SpUtil.getInstance().putString(BaseValue.User.HEADIMG, user.headerUrl);
        SpUtil.getInstance().putString(BaseValue.User.NICKNAME, user.nickname);

    }

    @Override
    public void loginErrorResult() {
        BaseValue.ISLOGIN = false;
    }
    //返回两次退出
    private long mExitTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, getResources().getText(R.string.exit_app), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}