package com.xzry.takeshow.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.ui.homepager.CityListSelectActivity;
import com.xzry.takeshow.ui.homepager.citylist.bean.CityInfoBean;
import com.xzry.takeshow.ui.mine.addaddress.widget.CityPicker;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 *  编辑地址
 */
public class AddressEditActivity extends BaseActivity implements View.OnClickListener{
    /*联系人 电话  所在地址 详细地址  */
    @BindView(R.id.titlebar)
    TitleBarView titleBarView;
    @BindView(R.id.add_address_linkman)
    EditText et_linkName;
    @BindView(R.id.add_address_phone)
    EditText et_phone;

    @BindView(R.id.add_address_area_lay)
    LinearLayout lay_addArea;

    @BindView(R.id.add_address_area)
    TextView tv_addArea;
    @BindView(R.id.address_info)
    EditText et_addInfo;
    @BindView(R.id.address_def)
    CheckBox et_addDef;


    @Override
    public int getLayout() {
        return R.layout.activity_address_edit;
    }

    @Override
    protected void initView() {
        titleBarView.setTitle("编辑地址");
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
        lay_addArea.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_address_area_lay:
                AddressSelection();
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();

                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");

                if (null == cityInfoBean)
                    return;

                //城市名称
                String cityName = cityInfoBean.getName();
                //纬度
                String latitude = cityInfoBean.getLongitude();
                //经度
                String longitude = cityInfoBean.getLatitude();


                tv_addArea.setText("城市： " + cityName + "\n" +
                        "经度： " + latitude + "\n" +
                        "纬度： " + longitude);
            }
        }
    }
    public void AddressSelection(){
        CityPicker cityPicker = new CityPicker.Builder(AddressEditActivity.this).textSize(20)
                .titleTextColor("#000000")
                .confirTextColor("#FF365D")
                .backgroundPop(0xa0000000)
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
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
                tv_addArea.setText(citySelected[0]  + citySelected[1] + citySelected[2] + "\n邮编：" + citySelected[3]);
            }

            @Override
            public void onCancel() {
                showShortToast("已取消");
            }
        });
    }


}
