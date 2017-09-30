package com.xzry.takeshow.ui.mine;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerPersonalDataComponent;
import com.xzry.takeshow.dagger.module.PersonalDataModule;
import com.xzry.takeshow.entity.UserData;
import com.xzry.takeshow.ui.mine.contract.PersonalDataContract;
import com.xzry.takeshow.ui.mine.presenter.PersonalDataPresenter;
import com.xzry.takeshow.utils.SpUtil;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.xzry.takeshow.base.BaseValue.User.NICKNAME;


/**
 *  个人资料
 */
public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View,View.OnClickListener{
    @BindView(R.id.titlebar_view)
    TitleBarView titleBarView;
    @BindView(R.id.personal_data_headimg)
    RelativeLayout lay_headImg;
    @BindView(R.id.personal_data_nickname)
    RelativeLayout lay_nickName;
    @BindView(R.id.personal_data_time)
    RelativeLayout lay_dataTime;
    @BindView(R.id.personal_dada_sex)
    RelativeLayout lay_sex;
    @BindView(R.id.personal_data_tv_headimg)
    ExpandImageView iv_headImg;
    @BindView(R.id.personal_data_tv_nickname)
    TextView tv_nickName;
    @BindView(R.id.personal_data_tv_datatime)
    TextView tv_dataTime;
    @BindView(R.id.personal_data_tv_sex)
    TextView tv_sex;
    @Inject
    PersonalDataPresenter mPresenter;
    //弹框男女
    TextView tv_man;//男
    TextView tv_woman;//女
    TextView tv_secrecy;//保密
    AlertDialog sexDialog;
    private List<String> permissionList;
    private DatePicker mDatePicker;
    private String nickName;
    private int mTag = 0;
    private AlertDialog.Builder mAlertDialog;

    @Override
    public int getLayout() {
        return R.layout.activity_personal_data;
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
    protected void onResume() {
        super.onResume();
        tv_nickName.setText(SpUtil.getInstance().getString(NICKNAME,nickName));
    }

    @Override
    protected void initData() {
        titleBarView.setTitle("个人资料");
        mPresenter.getAllData(BaseValue.TOKEN);
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
        lay_headImg.setOnClickListener(this);
        lay_nickName.setOnClickListener(this);
        lay_dataTime.setOnClickListener(this);
        lay_sex.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personal_data_headimg://
                new headerPopupWindows(this,view);
                break;
            case R.id.personal_data_nickname:
                NickNameEditActivity.toStartActivity(this,tv_nickName.getText().toString().trim());
                break;
            case R.id.personal_data_time:
                showDatePickerDialog();
                break;
            case R.id.personal_dada_sex:
                showSexDialog();
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);

                    LocalMedia media = selectList.get(0);
                    if (media.isCompressed()) {
                        String path = media.getCompressPath();
                        //开始更换头像
                        mPresenter.toModifyHead(BaseValue.TOKEN,path);
                    }
                    break;
            }
        }
    }


    @Override
    public void resultData(UserData data) {
        if (data == null) {
            return;
        }
        iv_headImg.setImageURI(data.headerUrl);
        tv_nickName.setText(data.nickname);
        tv_dataTime.setText(data.birthday);
        if (TextUtils.equals(data.sex,"1")) {
            tv_sex.setText("男");
        }else {
            tv_sex.setText("女");
        }
        nickName = data.nickname;
    }

    @Override
    public void resultHeadImgData(String str) {
        if (!TextUtils.equals(str,"")) {
            iv_headImg.setImageURI(str);
        }
    }

    @Override
    public void resultNickNameData(String name) {

    }


    //更换头像
    public class headerPopupWindows extends PopupWindow {
        private headerPopupWindows(Context context, View parent) {
            View view = View.inflate(context, R.layout.item_popupwindow, null);
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in_2));
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();
            Button btn1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
            Button btn2 = (Button) view.findViewById(R.id.item_popupwindows_Photo2);
            Button btn3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
            btn1.setOnClickListener(camera);
            btn2.setOnClickListener(takePhotos);
            btn3.setOnClickListener(cancel);

        }

        //拍照点击事件
        private View.OnClickListener camera = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWESPermission();
                if (!permissionList.isEmpty()) {
                    String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions(PersonalDataActivity.this, permissions, 2);
                } else {

                    Log.i("pp","-----------------进来了");
                    PictureSelector.create(PersonalDataActivity.this)
                            .openCamera(PictureMimeType.ofImage())
                            .selectionMode(PictureConfig.SINGLE)
                            .enableCrop(true)
                            .compress(true)
                            .freeStyleCropEnabled(false)
                            .withAspectRatio(1,1)
                            .showCropFrame(false)
                            .showCropGrid(false)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                     //.circleDimmedLayer(true)//// 是否圆形裁剪 true or false
                }
                dismiss();
            }
        };



        //从相册获取图片
        private View.OnClickListener takePhotos = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWESPermission();
                if (!permissionList.isEmpty()) {
                    String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions(PersonalDataActivity.this, permissions, 2);
                } else {
                    PictureSelector.create(PersonalDataActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .selectionMode(PictureConfig.SINGLE)
                            .enableCrop(true)
                            .compress(true)
                            .freeStyleCropEnabled(false)
                            .withAspectRatio(1,1)
                            .showCropFrame(false)
                            .showCropGrid(false)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    // .circleDimmedLayer(true)//// 是否圆形裁剪 true or false

                }

                dismiss();
            }
        };
        //取消按钮
        private View.OnClickListener cancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
    }
    //申请使用相机和文件访问的权限
    public void openWESPermission() {
        permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

    }
    /**
     * 显示日期选择器
     */
    public void showDatePickerDialog() {
        mTag=1;
        View view = initDatePicker();
        mAlertDialog = new AlertDialog.Builder(this);
        mAlertDialog.setTitle("选择时间");
        initDialog(view);
        mAlertDialog.show();
    }
    /**
     * 初始化DatePicker
     *
     * @return
     */
    private View initDatePicker() {
        View inflate = LayoutInflater.from(this).inflate(
                R.layout.item_dateandtimepicker, null);
        mDatePicker = (DatePicker) inflate
                .findViewById(R.id.datePicker);
        resizePikcer(mDatePicker);
        return inflate;
    }
    /**
     * 调整FrameLayout大小
     *
     * @param tp
     */
    private void resizePikcer(FrameLayout tp) {
        List<NumberPicker> npList = findNumberPicker(tp);
        for (NumberPicker np : npList) {
            resizeNumberPicker(np);
        }
    }
    /*
   * 调整numberpicker大小
   */
    private void resizeNumberPicker(NumberPicker np) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);
        np.setLayoutParams(params);
    }
    /**
     * 得到viewGroup里面的numberpicker组件
     *
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {

                        return result;
                    }
                }
            }
        }
        return npList;
    }

    /**
     * 创建dialog
     *
     * @param view
     */
    private void initDialog(View view) {
        mAlertDialog.setPositiveButton("确定",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();


                    }
                });
        mAlertDialog.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        dialog.dismiss();
                    }
                });
        mAlertDialog.setView(view);
    }
    /**
     *  性别dialog
     */
    public void showSexDialog(){
        sexDialog = new AlertDialog.Builder(this).create();
        sexDialog.show();
        sexDialog.getWindow().setContentView(R.layout.set_sex_select);
        tv_man = (TextView) sexDialog.findViewById(R.id.set_man);
        tv_woman = (TextView) sexDialog.findViewById(R.id.set_woman);
        tv_secrecy = (TextView) sexDialog.findViewById(R.id.set_secrecy);
        tv_man.setOnClickListener(new sexOnClick());
        tv_woman.setOnClickListener(new sexOnClick());
    }
    class sexOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //写到本地
//            if (view == tv_man) {
//                if (SpUtil.getInstance(getApplicationContext()).getInt(SEX,0) == 1) {
//                    return;
//                }
//                sex = 1;
//            }else if (view == tv_woman){
//                if (SpUtil.getInstance().getInt(SEX,0) == 2) {
//                    return;
//                }
//                sex = 2;
//            }
//            //开始网络请求
//            final BasicInfoEntity basicInfoEntity = new BasicInfoEntity();
//            basicInfoEntity.id = SpUtil.getInstance().getInt(USERID,0);
//            basicInfoEntity.code = SpUtil.getInstance().getString(CODE,"");
//            basicInfoEntity.sex = sex;
//            MineApi.toModifySex(basicInfoEntity, new ProgressSubscriber<String>(new SubscriberOnNextListener<String>() {
//
//                @Override
//                public void onNext(String result) {
//                    HttpResult res = GsonUtil.json2bean(result,HttpResult.class);
//                    if (res.getState().equals("success")) {
//                        //成功后本地缓存
//                        if (sex == 1){
//                            tv_sex.setText("男");
//                        }else if (sex == 2){
//                            tv_sex.setText("女");
//                        }
//                        SpUtil.getInstance().putInt(SEX,sex);
//                        showShortToast("保存成功");
//                        sexDialog.dismiss();
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//            }, SetGerenActivity.this));
        }
    }
}
