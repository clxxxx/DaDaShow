package com.xzry.takeshow.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerAddressComponent;
import com.xzry.takeshow.dagger.module.AddressModule;
import com.xzry.takeshow.entity.AddressEntity;
import com.xzry.takeshow.ui.mine.addaddress.widget.CityPicker;
import com.xzry.takeshow.ui.mine.contract.AddressContract;
import com.xzry.takeshow.ui.mine.presenter.AddressPresenter;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.xzry.takeshow.ui.homepager.SearchHomeActivity.manager;

public class ADDaddressActivity extends BaseActivity implements AddressContract.View,View.OnClickListener{
    /*联系人 电话  所在地址 详细地址  */
    @BindView(R.id.titlebar)
    TitleBarView titleBarView;
    @BindView(R.id.add_address_linkman)
    EditText et_linkName;
    @BindView(R.id.add_address_phone)
    EditText et_phone;

    @BindView(R.id.add_address_area_lay)
    LinearLayout lay_addArea;

    @BindView(R.id.add_address_area1)
    TextView tv_addArea1;
    @BindView(R.id.add_address_area2)
    TextView tv_addArea2;
    @BindView(R.id.add_address_area3)
    TextView tv_addArea3;

    @BindView(R.id.address_info)
    EditText et_addInfo;
    @BindView(R.id.address_def)
    CheckBox ck_isDefault;
    @BindView(R.id.address_save)
    TextView tv_save;
    @Inject
    AddressPresenter mPresenter;

    @Override
    public int getLayout() {
        return R.layout.activity_addaddress;
    }

    @Override
    protected void initView() {
        DaggerAddressComponent.builder()
                .appComponent(getAppComponent())
                .addressModule(new AddressModule(this))
                .build()
                .inject(this);
        titleBarView.setTitle("添加地址");
    }

    @Override
    protected void initData() {
        AddressEntity item = (AddressEntity) getIntent().getSerializableExtra("item");
        if (item != null) {
            et_linkName.setText(item.consignee);
            et_phone.setText(item.mobile);
            tv_addArea1.setText(item.province);
            tv_addArea2.setText(item.city);
            tv_addArea3.setText(item.district);
            et_addInfo.setText(item.address);
            if (item.isDefault == 1){
                ck_isDefault.setChecked(true);
            }else {
                ck_isDefault.setChecked(false);
            }

        }
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
        lay_addArea.setOnClickListener(this);
        tv_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_address_area_lay:
                AddressSelection();
                break;
            case R.id.address_save:
                saveData();
                break;
            default:
                break;
        }
    }
    public void saveData(){
        Map mp = new HashMap();
        AddressEntity entity = new AddressEntity();
        entity.consignee = et_linkName.getText().toString().trim();
        entity.mobile = et_phone.getText().toString().trim();
        entity.province = tv_addArea1.getText().toString().trim();
        entity.city = tv_addArea2.getText().toString().trim();
        entity.district = tv_addArea3.getText().toString().trim();
        entity.address = et_addInfo.getText().toString().trim();
        if (ck_isDefault.isChecked()) {
            entity.isDefault = 1;
        }else {
            entity.isDefault = 0;
        }
        if (entity.mobile.length()<=0) {
            showShortToast("手机号码不能为空");

            return;
        }
        if (!entity.mobile.matches("^1[0-9]{10}$")) {
            showShortToast("请输入正确的手机号！");
            return;
        }
        Log.i("add","-------------xuan-"+entity.isDefault);
        mp.put("token", BaseValue.TOKEN);
        mp.put("orderConsignee", entity.toString());
        mPresenter.toAddAddress(mp);
    }
    public void AddressSelection(){
        hideInput(this);
        CityPicker cityPicker = new CityPicker.Builder(ADDaddressActivity.this).textSize(20)
                .titleTextColor("#000000")
                .confirTextColor("#FF365D")
                .backgroundPop(0xa0000000)
                .province("广东省")
                .city("深圳市")
                .district("罗湖区")
                .textSize(15)
                .textColor(R.color.black)

                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();
        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                tv_addArea1.setText(citySelected[0]);
                tv_addArea2.setText(citySelected[1]);
                tv_addArea3.setText(citySelected[2]);
//                + "\n邮编：" + citySelected[3]
            }

            @Override
            public void onCancel() {
                showShortToast("已取消");
            }
        });
    }
    public void hideInput(Context context) {
        if(manager==null){
            manager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        manager.hideSoftInputFromWindow(((Activity) context)
                        .getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void resultData(List<AddressEntity> list) {

    }

    @Override
    public void refreshDisplay(String str) {
        if (str.equals("success")) {
            finish();
        }
    }
}
