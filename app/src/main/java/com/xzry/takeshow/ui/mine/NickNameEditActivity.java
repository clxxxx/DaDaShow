package com.xzry.takeshow.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerPersonalDataComponent;
import com.xzry.takeshow.dagger.module.PersonalDataModule;
import com.xzry.takeshow.entity.UserData;
import com.xzry.takeshow.ui.mine.contract.PersonalDataContract;
import com.xzry.takeshow.ui.mine.presenter.PersonalDataPresenter;
import com.xzry.takeshow.widget.TitleBarView;

import javax.inject.Inject;

import butterknife.BindView;
/**
 *  修改昵称
 */
public class NickNameEditActivity extends BaseActivity implements PersonalDataContract.View,View.OnClickListener,TextWatcher{
    @BindView(R.id.titlebar_view)
    TitleBarView titleBarView;
    @BindView(R.id.nickname_edit)
    EditText et_nickName;
    @BindView(R.id.nickname_clear)
    ImageView iv_clear;
    @BindView(R.id.nickname_confirm)
    TextView tv_confirm;
    @Inject
    PersonalDataPresenter mPresenter;
    public static void toStartActivity(Context context,String nickname){
        Intent intent = new Intent(context, NickNameEditActivity.class);
        intent.putExtra("nickname",nickname);
        context.startActivity(intent);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_nick_name_edit;
    }

    @Override
    protected void initView() {
        DaggerPersonalDataComponent.builder()
                .appComponent(getAppComponent())
                .personalDataModule(new PersonalDataModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        titleBarView.setTitle("修改昵称");
        String nickname = getIntent().getStringExtra("nickname");
        et_nickName.setText(nickname);


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
        et_nickName.addTextChangedListener(this);

        iv_clear.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nickname_clear://
                et_nickName.getText().clear();
                break;
            case R.id.nickname_confirm://
                mPresenter.toModifyNickName(BaseValue.TOKEN,getNickName());
                break;
            default:
                break;
        }
    }
    public String getNickName(){
        return et_nickName.getText().toString().trim();
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


        if (getNickName().length()>0) {
            iv_clear.setVisibility(View.VISIBLE);
            tv_confirm.setEnabled(true);
            tv_confirm.setBackgroundResource(R.color.theme_dark_color);
        }else {
            iv_clear.setVisibility(View.GONE);
            tv_confirm.setEnabled(false);
            tv_confirm.setBackgroundResource(R.color.text_color_1);
        }

    }

    @Override
    public void resultData(UserData data) {

    }

    @Override
    public void resultHeadImgData(String str) {

    }

    @Override
    public void resultNickNameData(String name) {
        if (!TextUtils.equals(name,"")) {
            finish();
        }
    }
}
