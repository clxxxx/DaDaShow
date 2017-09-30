package com.xzry.takeshow.ui.mine;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerMineFragmentComponent;
import com.xzry.takeshow.dagger.module.MineFragmentModule;
import com.xzry.takeshow.entity.UserData;
import com.xzry.takeshow.ui.aftersale.AfterSaleOrderActivity;
import com.xzry.takeshow.ui.integralshop.IntegralShopActivity;
import com.xzry.takeshow.ui.integralshop.MyIntegralActivity;
import com.xzry.takeshow.ui.mine.contract.MineFragmentContract;
import com.xzry.takeshow.ui.mine.presenter.MineFragmentPresenter;
import com.xzry.takeshow.ui.order.OrderActivity;
import com.xzry.takeshow.utils.SpUtil;

import javax.inject.Inject;

import butterknife.BindView;


public class MineFragment extends BaseFragment implements MineFragmentContract.View,View.OnClickListener{
    private static final String TAG = "mine";
    /*设置 消息*/
    @BindView(R.id.mine_set)
    ImageView iv_set;
    @BindView(R.id.mine_message)
    ImageView iv_message;
    /*头像  昵称*/
    @BindView(R.id.mine_head_bg_img)
    ImageView iv_headImgBg;
    @BindView(R.id.mine_head_img)
    ExpandImageView iv_headImg;
    @BindView(R.id.mine_nikename)
    TextView tv_nickName;
    @BindView(R.id.mine_attention)
    TextView tv_attention;
    @BindView(R.id.mine_fans)
    TextView tv_fans;
    @BindView(R.id.mine_attention_lay)
    LinearLayout lay_attention;
    @BindView(R.id.mine_fans_lay)
    LinearLayout lay_fans;
    /*积分收藏足迹*/
    @BindView(R.id.mine_integral)
    LinearLayout lay_integral;
    @BindView(R.id.mine_collect)
    LinearLayout lay_collect;
    @BindView(R.id.mine_footprint)
    LinearLayout lay_footprint;
    @BindView(R.id.mine_integral_tx)
    TextView tv_integral;
    @BindView(R.id.mine_collect_text)
    TextView tv_collect;
    @BindView(R.id.mine_footprint_text)
    TextView tv_footprint;
    /*订单*/
    /*待付款 待发货 待收款 待评价 退款/售后*/
    @BindView(R.id.mine_indent_lay)
    RelativeLayout lay_indent;
    @BindView(R.id.mine_indent_lay_1)
    RelativeLayout lay_indent1;
    @BindView(R.id.mine_indent_lay_2)
    RelativeLayout lay_indent2;
    @BindView(R.id.mine_indent_lay_3)
    RelativeLayout lay_indent3;
    @BindView(R.id.mine_indent_lay_4)
    RelativeLayout lay_indent4;
    @BindView(R.id.mine_indent_lay_5)
    RelativeLayout lay_indent5;
    @BindView(R.id.mine_indent_text1)
    TextView tv_indentTv1;
    @BindView(R.id.mine_indent_text2)
    TextView tv_indentTv2;
    @BindView(R.id.mine_indent_text3)
    TextView tv_indentTv3;
    @BindView(R.id.mine_indent_text4)
    TextView tv_indentTv4;
    /*积分商城 优惠券 我的地址  帮助与客服  意见与反馈 加入搭搭*/
    @BindView(R.id.mine_points_mall)
    RelativeLayout lay_points_mall;
    @BindView(R.id.mine_coupon)
    RelativeLayout lay_coupon;
    @BindView(R.id.mine_my_address)
    RelativeLayout lay_myAddress;
    @BindView(R.id.mine_help)
    RelativeLayout lay_help;
    @BindView(R.id.mine_opinions_and_feedback)
    RelativeLayout lay_opAndFeedback;
    @BindView(R.id.mine_join)
    RelativeLayout lay_join;
    @Inject
    MineFragmentPresenter mPresenter;
    private boolean isInit = false;
    public static MineFragment newInstance() {
        return new MineFragment();
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
       //setStatusBarHeight();

        DaggerMineFragmentComponent.builder()
                .appComponent(getAppComponent())
                .mineFragmentModule(new MineFragmentModule(this))
                .build()
                .inject(this);

//        Eyes.setStatusBarLightMode(getActivity(), Color.WHITE, false);
//        Eyes.translucentStatusBar(getActivity(), false);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        iv_set.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        lay_integral.setOnClickListener(this);

        lay_points_mall.setOnClickListener(this);
        lay_coupon.setOnClickListener(this);
        lay_myAddress.setOnClickListener(this);
        lay_help.setOnClickListener(this);
        lay_opAndFeedback.setOnClickListener(this);
        lay_join.setOnClickListener(this);
        lay_help.setOnClickListener(this);
        iv_headImgBg.setOnClickListener(this);
        lay_attention.setOnClickListener(this);
        lay_fans.setOnClickListener(this);
        lay_collect.setOnClickListener(this);
        lay_footprint.setOnClickListener(this);
        lay_indent1.setOnClickListener(this);
        lay_indent2.setOnClickListener(this);
        lay_indent3.setOnClickListener(this);
        lay_indent4.setOnClickListener(this);
        lay_indent5.setOnClickListener(this);

    }



    public void getMineData(){

        mPresenter.toGetAll(BaseValue.TOKEN);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mine_set://设置
                toActivity(SettingActivity.class);
                break;
            case R.id.mine_message://消息

                break;
            case R.id.mine_attention_lay: //关注
                AttentionActivity.startActivity(getActivity(),1);
                break;
            case R.id.mine_fans_lay:  //粉丝
                AttentionActivity.startActivity(getActivity(),2);
                break;
            case R.id.mine_integral:   //我的积分
                toActivity(MyIntegralActivity.class);
                break;
            case R.id.mine_collect:   //我的收藏
                CollectActivity.startActivity(getActivity());
                break;
            case R.id.mine_footprint: //我的足迹
                FootMarkActivity.startActivity(getActivity());
                break;

            case R.id.mine_points_mall://积分与商城
                IntegralShopActivity.toActivity(getActivity());
                break;
            case R.id.mine_coupon://优惠券

                break;
            case R.id.mine_my_address://我的地址
                AddressActivity.initActivity(getActivity());
                break;
            case R.id.mine_help://客服与帮助
                toActivity(HelpServiceActivity.class);
                break;
            case R.id.mine_opinions_and_feedback://意见与反馈
                toActivity(OpinionsAndFeedbackActivity.class);
                break;
            case R.id.mine_join://加入搭搭
                toActivity(JoinDaDaActivity.class);
                break;
            case R.id.mine_indent_lay_1://订单状态
                OrderActivity.intentOrderActivity(getActivity(), 0);
                break;
            case R.id.mine_indent_lay_2://订单状态
                OrderActivity.intentOrderActivity(getActivity(), 1);
                break;
            case R.id.mine_indent_lay_3://订单状态
                OrderActivity.intentOrderActivity(getActivity(), 2);
                break;
            case R.id.mine_indent_lay_4://订单状态
                OrderActivity.intentOrderActivity(getActivity(), 3);
                break;
            case R.id.mine_indent_lay_5://售后
                AfterSaleOrderActivity.intentAfterSaleOrderActivity(getActivity());
                break;
            default:
                break;
        }
    }

    public void setStatusBarHeight(){
        isInit = true;
        ViewTreeObserver viewTreeObserver2 = iv_headImgBg.getViewTreeObserver();
        viewTreeObserver2.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) iv_headImgBg.getLayoutParams();
                lp.width = ScreenUtil.getScreenWidth(getActivity());
                lp.height = lp.width*382/750 ;
                iv_headImgBg.setLayoutParams(lp);
                return true;
            }
        });
    }

    @Override
    public void resultData(UserData data) {
        if (data == null) {
            return;
        }
        iv_headImg.setImageURI(data.headerUrl);
        tv_nickName.setText(data.nickname);
        tv_attention.setText(data.followCount);
        tv_fans.setText(data.fansCount);
        tv_collect.setText(data.favoriteCount);
        tv_footprint.setText(data.footPrintCount);
        iv_headImg.setImageURI(SpUtil.getInstance().getString(BaseValue.User.HEADIMG,""));
        tv_nickName.setText(SpUtil.getInstance().getString(BaseValue.User.NICKNAME,""));
//        Uri uri = Uri.parse(SpUtil.getInstance().getString(BaseValue.User.HEADIMG,"R.mipmap.default_avatar"));
//        iv_headImg.setImageURI(uri);
//        tv_nickName.setText(SpUtil.getInstance().getString(BaseValue.User.NICKNAME,BaseValue.User.MOBILE));
    }
}
