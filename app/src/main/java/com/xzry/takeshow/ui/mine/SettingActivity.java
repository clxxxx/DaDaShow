package com.xzry.takeshow.ui.mine;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.main.MainActivity;
import com.xzry.takeshow.utils.PopupWindowUtil;
import com.xzry.takeshow.utils.SpUtil;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;
/**
 *  设置中心
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.titlebar_view)
    TitleBarView titleBarView;
    @BindView(R.id.setting_personal_data)
    RelativeLayout lay_personalData;
    @BindView(R.id.setting_security_center)
    RelativeLayout lay_securityCenter;
    @BindView(R.id.setting_clear_cache)
    RelativeLayout lay_clearCache;
    @BindView(R.id.setting_exit)
    TextView tv_exit;
    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        titleBarView.setTitle("设置");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        titleBarView.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        lay_personalData.setOnClickListener(this);
        lay_securityCenter.setOnClickListener(this);
        lay_clearCache.setOnClickListener(this);
        tv_exit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_personal_data://个人资料
                toActivity(PersonalDataActivity.class);
                break;
            case R.id.setting_security_center://安全中心

                break;
            case R.id.setting_clear_cache://清除缓存
                clearCache();
                break;
            case R.id.setting_exit:
                showExit();
                break;
            default:
                break;
        }
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
                pop.dissPopWin();
                showShortToast("退出成功");
                SpUtil.getInstance().clear();
                MainActivity.startActivity(SettingActivity.this);
                finish();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShortToast("取消了");
                pop.dissPopWin();
            }
        });
    }
    private void clearSP(){

    }
    private void clearCache() {
        View rootView = findViewById(R.layout.activity_setting);
        View view = this.getLayoutInflater().inflate(R.layout.item_setting_dialog, null);
        final PopupWindowUtil pop = new PopupWindowUtil();
        pop.showBottom(getWindow(),rootView,view);

        TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShortToast("退出成功");
                pop.dissPopWin();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dissPopWin();
            }
        });
    }
}
