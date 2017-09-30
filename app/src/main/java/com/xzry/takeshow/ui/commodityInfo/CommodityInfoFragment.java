package com.xzry.takeshow.ui.commodityInfo;

import android.content.Intent;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.util.GlideImageLoader;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.RequestEvent;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.ui.homepager.BrandShopActivity;
import com.xzry.takeshow.ui.login.LoginActivity;
import com.xzry.takeshow.ui.main.MainActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;



/**
 * Created by 周东阳 on 2017/8/9 0009.
 */

public class CommodityInfoFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.commodity_banner)
    Banner commodity_banner;
    @BindView(R.id.goods_name)
    TextView goods_name;
    @BindView(R.id.goods_subName)
    TextView goods_subName;
    @BindView(R.id.goods_price)
    TextView goods_price;
    @BindView(R.id.tv_price_old)
    TextView tv_price_old;
    @BindView(R.id.buyer_discuss)
    LinearLayout buyer_discuss;
    @BindView(R.id.isOnline)
    LinearLayout isOnline;
    @BindView(R.id.subject)
    LinearLayout subject;
    @BindView(R.id.subject_name)
    TextView subject_name;
    @BindView(R.id.choose_size)
    LinearLayout choose_size;
    @BindView(R.id.commodity_discuss_count)
    TextView discusscount;
    @BindView(R.id.commodity_discuss_hint)
    TextView discusshint;
    @BindView(R.id.discuss_info)
    LinearLayout discuss_info;
    @BindView(R.id.shop_name)
    TextView shop_name;
    @BindView(R.id.location_info)
    TextView location_info;
    @BindView(R.id.discuss_img)
    ImageView discuss_img;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.discuss_size)
    TextView discuss_size;
    @BindView(R.id.avatar_img)
    ExpandImageView avatar_img;

    @BindView(R.id.service)
    LinearLayout service;
    @BindView(R.id.store)
    LinearLayout store;
    @BindView(R.id.collect)
    LinearLayout collect;
    @BindView(R.id.bottom_btn1)
    TextView bottom_btn1;
    @BindView(R.id.bottom_btn2)
    TextView bottom_btn2;
    @BindView(R.id.commodity_inventory)
    TextView commodity_inventory;

    private Goods goods;

    public static CommodityInfoFragment newInstance() {
        return new CommodityInfoFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_commodity_info;
    }

    @Override
    protected void initView() {

        tv_price_old.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        choose_size.setOnClickListener(this);
        buyer_discuss.setOnClickListener(this);
        bottom_btn1.setOnClickListener(this);
        bottom_btn2.setOnClickListener(this);
        store.setOnClickListener(this);
        collect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CommodityParameterFragment pDialogFragment;
        switch (v.getId())
        {
            case R.id.choose_size:
                pDialogFragment = new CommodityParameterFragment(getContext(), goods, 0);
                pDialogFragment.show(getActivity().getFragmentManager(), "pDialogFragment");
                break;
            case R.id.buyer_discuss:
                CommodityDiscussActivity.intentCommodityDiscuss(getActivity());
                break;
            case R.id.bottom_btn1:
                if (!TextUtils.equals(goods.status, "Online"))
                    return;
                if (!BaseValue.ISLOGIN){
                    LoginActivity.startActivity(getActivity());
                    return;
                }
                if (goods.stock == 0)
                {
                    final DialogArrivalNotification dialog = new DialogArrivalNotification(getActivity());
                    dialog.getCancel().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.getConfirm().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.getClose().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.getPhone().setText("");
                        }
                    });
                    dialog.getPhone().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (TextUtils.isEmpty(s.toString())){
                                dialog.getClose().setVisibility(View.VISIBLE);
                            }else {
                                dialog.getClose().setVisibility(View.GONE);
                            }
                        }
                    });
                    dialog.show();
                }else {
                    pDialogFragment = new CommodityParameterFragment(getContext(), goods, 1);
                    pDialogFragment.show(getActivity().getFragmentManager(), "pDialogFragment");
                }
                break;
            case R.id.bottom_btn2:
                if (!BaseValue.ISLOGIN){
                    LoginActivity.startActivity(getActivity());
                    return;
                }
                pDialogFragment = new CommodityParameterFragment(getContext(), goods, 2);
                pDialogFragment.show(getActivity().getFragmentManager(), "pDialogFragment");
                break;
            case R.id.store:
                BrandShopActivity.toStartActivity(getActivity(), goods.storeInfo.storeID);
                break;
            case R.id.collect:
                if (BaseValue.ISLOGIN){
                    RequestEvent event = new RequestEvent();

                    Map<String,String> map = new HashMap<>();
                    map.put("sku",goods.sku);
                    map.put("token",BaseValue.TOKEN);

                    event.setType(RequestEvent.COLLECT);
                    event.setMap(map);
                    event.setConsumer(consumer);

                    EventBus.getDefault().post(event);
                } else {
                    LoginActivity.startActivity(getActivity());
                }
                break;
        }
    }

    //获取banner图
    private void initBanner(boolean showTitle, List<String> images, List<String> titles){
        if (showTitle) {
            //设置标题集合（当banner样式有显示title时）
            commodity_banner.setBannerTitles(titles);
            commodity_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);//有多少图片就需要多少标题
        } else {
            commodity_banner.setIndicatorGravity(BannerConfig.CENTER);
        }
        //设置图片加载器
        commodity_banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        commodity_banner.setImages(images);
        //设置自动轮播，默认为true
        commodity_banner.isAutoPlay(false);
        //banner设置方法全部调用完毕时最后调用
        commodity_banner.start();
    }

    public void setData(Goods goods){
        if (goods.spu == null){
            collect.setVisibility(View.GONE);
            bottom_btn1.setBackgroundResource(R.color.text_color_2);
            bottom_btn1.setText("已下架");
            bottom_btn2.setVisibility(View.GONE);
            return;
        }

        this.goods = goods;

        initBanner(false, goods.banner, goods.banner);
        goods_name.setText(goods.goodsName);
        goods_subName.setText(goods.subName);
        goods_price.setText("￥" + goods.shopPrice);
        if (goods.isOnline == 1)
            isOnline.setVisibility(View.GONE);
        if (TextUtils.isEmpty(goods.subjectName))
        {
            subject.setVisibility(View.GONE);
        } else {
            subject_name.setText("活动：" + goods.subjectName);
        }
        discusscount.setText(getString(R.string.commodity_discuss_count, goods.comment.count));
        if (goods.comment.count == 0){
            discusshint.setVisibility(View.VISIBLE);
            discuss_img.setVisibility(View.GONE);
        }else {
            discuss_info.setVisibility(View.VISIBLE);
            avatar_img.setImageURI(goods.comment.headerUrl);
            nickname.setText(goods.comment.nickname);
            comment.setText(goods.comment.comment);
            discuss_size.setText("已选择：" + goods.comment.color + "; " + goods.comment.size + ";");
        }

        if (TextUtils.equals(goods.status, "Online"))
        {
            if (goods.stock == 0)
            {
                bottom_btn2.setVisibility(View.GONE);
                bottom_btn1.setText("到货通知");
                bottom_btn1.setBackgroundColor(getResources().getColor(R.color.theme_light_color2));
            }else {

            }
        } else {
            collect.setVisibility(View.GONE);
            bottom_btn2.setVisibility(View.GONE);
            bottom_btn1.setText("已下架");
            bottom_btn1.setBackgroundColor(getResources().getColor(R.color.text_color_2));
        }

        shop_name.setText(goods.storeInfo.storeName);
        location_info.setText(goods.storeInfo.address);
    }


    DisposableObserver consumer = new ErrorDisposableObserver<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
            String result = null;
            try {
                result = responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpResult httpResult = new Gson().fromJson(result, HttpResult.class);
            if (TextUtils.equals(httpResult.getState() , "success")) {
                Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
        }
    };

    @Subscribe
    public void commodityInventory(String event){
        commodity_inventory.setText("已选择： " + event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销事件接受
        EventBus.getDefault().unregister(this);
    }
}
