package com.xzry.takeshow.ui.commodityInfo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.CommodityStock;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.RequestEvent;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.request.AddShoppingCar;
import com.xzry.takeshow.ui.order.ConfirmOrderActivity;
import com.xzry.takeshow.widget.AutoNewLineLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/8/11 0011.
 */

public class CommodityParameterFragment extends DialogFragment {

    private ExpandImageView goodsimg;
    private TextView stock;
    private TextView price;
    private ImageView close;
    private AutoNewLineLayout commodity_color;
    private AutoNewLineLayout commodity_size;
    private Goods goods;
    private Context mContext;
    private String color;
    private String size;
    private TextView chooseColorTv;
    private TextView chooseSizeTv;
    private TextView specification;
    private TextView add;
    private TextView sub;
    private TextView add_shopping_car;
    private TextView purchase;
    private TextView affirm;
    private EditText commodity_number;
    private int commoditynumber = 1;
    private int commoditystock;
    private RequestEvent event;
    private int type;
    private String sku;
    private String goodsPrice;

    public CommodityParameterFragment(Context mContext, Goods goods, int type){
        this.mContext = mContext;
        this.goods = goods;
        this.type = type;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_commdity_parameter);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        commoditystock = goods.stock;

        specification = (TextView) dialog.findViewById(R.id.specification);

        goodsimg = (ExpandImageView) dialog.findViewById(R.id.goods_img);
        goodsimg.setImageURI(goods.banner.get(0));
        price = (TextView) dialog.findViewById(R.id.price);
        price.setText("￥" + goods.shopPrice);
        stock = (TextView) dialog.findViewById(R.id.stock);
        stock.setText(getString(R.string.commodity_stock, goods.stock));
        close = (ImageView) dialog.findViewById(R.id.close_iv);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        commodity_number = (EditText) dialog.findViewById(R.id.commodity_number);
        sub = (TextView) dialog.findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commoditynumber != 1)
                {
                    commoditynumber -= 1;
                    commodity_number.setText(commoditynumber+"");
                }
            }
        });
        add_shopping_car = (TextView) dialog.findViewById(R.id.add_shopping_car);
        add_shopping_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShoppingCar();
            }
        });
        purchase = (TextView) dialog.findViewById(R.id.purchase);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods.itemNum = commoditynumber;
                ConfirmOrderActivity.intentConfirmOrderActivity(mContext, goods, "1");
                dismiss();
            }
        });
        affirm = (TextView) dialog.findViewById(R.id.affirm);
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1){
                    addShoppingCar();
                }else {
                    goods.itemNum = commoditynumber;
                    ConfirmOrderActivity.intentConfirmOrderActivity(mContext, goods, "1");
                    dismiss();
                }
            }
        });
        if (type == 0){
            add_shopping_car.setVisibility(View.VISIBLE);
            purchase.setVisibility(View.VISIBLE);
            affirm.setVisibility(View.GONE);
        }else {
            add_shopping_car.setVisibility(View.GONE);
            purchase.setVisibility(View.GONE);
            affirm.setVisibility(View.VISIBLE);
        }
        add = (TextView) dialog.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commoditynumber < commoditystock)
                {
                    commoditynumber += 1;
                    commodity_number.setText(commoditynumber + "");
                }else {
//                    Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        commodity_color = (AutoNewLineLayout) dialog.findViewById(R.id.commodity_color);
        commodity_size = (AutoNewLineLayout) dialog.findViewById(R.id.commodity_size);

        if (goods.colors.size() > 0)
            for (int i = 0; i < goods.colors.size(); i++) {
                final TextView tv = new TextView(getActivity());
                tv.setTag(i);
                tv.setText(goods.colors.get(i));
                tv.setPadding(40,20,40,20);
                tv.setTextSize(12);
                tv.setTextColor(getResources().getColor(R.color.little_title_color));
                tv.setBackgroundResource(R.drawable.commodity_colors_selected_bg);
                if (i == 0){
                    tv.setSelected(true);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    chooseColorTv = tv;
                }
                commodity_color.addView(tv);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (tv != chooseColorTv) {
                            tv.setSelected(true);
                            tv.setTextColor(getResources().getColor(R.color.white));
                            chooseColorTv.setSelected(false);
                            chooseColorTv.setTextColor(getResources().getColor(R.color.little_title_color));
                            chooseColorTv.setBackgroundResource(R.drawable.commodity_colors_selected_bg);
                            chooseColorTv = tv;

                            color = tv.getText().toString();
                            if (event == null)
                                event = new RequestEvent();
                            Map<String,String> map = new HashMap<>();
                            map.put("color",color);
                            map.put("size",size);
                            map.put("spu",goods.spu);
                            map.put("storeID",goods.storeInfo.storeID);

                            event.setType(RequestEvent.CHECKSTOCK);
                            event.setMap(map);
                            event.setConsumer(getDisposableObserver());
                            EventBus.getDefault().post(event);

                            specification.setText("已选择：" + color + ";   " + size+ ";");

                        }
                    }
                });
            }

        if (goods.sizes.size() > 0)
            for (int i = 0; i < goods.sizes.size(); i++) {
                final TextView tv = new TextView(getActivity());
                tv.setTag(i);
                tv.setText(goods.sizes.get(i));
                tv.setPadding(40,20,40,20);
                tv.setTextSize(14);
                tv.setTextColor(getResources().getColor(R.color.little_title_color));
                tv.setBackgroundResource(R.drawable.commodity_colors_selected_bg);
                commodity_size.addView(tv);
                if (i == 0){
                    tv.setSelected(true);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    chooseSizeTv = tv;
                }
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (tv != chooseSizeTv){
                            tv.setSelected(true);
                            tv.setTextColor(getResources().getColor(R.color.white));
                            chooseSizeTv.setSelected(false);
                            chooseSizeTv.setTextColor(getResources().getColor(R.color.little_title_color));
                            chooseSizeTv.setBackgroundResource(R.drawable.commodity_colors_selected_bg);
                            chooseSizeTv = tv;

                            size = tv.getText().toString();
                            Log.i("size",size);
                            if (event == null)
                                event = new RequestEvent();
                            Map<String,String> map = new HashMap<>();
                            map.put("color",color);
                            map.put("size",size);
                            map.put("spu",goods.spu);
                            map.put("storeID",goods.storeInfo.storeID);

                            event.setMap(map);
                            event.setConsumer(getDisposableObserver());
                            EventBus.getDefault().post(event);

                            specification.setText("已选择：" + color + ";   " + size+ ";");
                        }
                    }
                });
            }
        if (goods.colors.size() > 0)
            color = goods.colors.get(0);
        if (goods.sizes.size() > 0)
            size = goods.sizes.get(0);

        goodsPrice = goods.shopPrice;
        return dialog;
    }

    private void addShoppingCar(){
        if (event == null)
            event = new RequestEvent();

        Map<String,String> map = new HashMap<>();
        map.put("token", BaseValue.TOKEN);

        AddShoppingCar addShoppingCar = new AddShoppingCar();
        addShoppingCar.setCouponId("");
        addShoppingCar.setStoreId(goods.storeInfo.storeID);
        addShoppingCar.setSku(sku);
        addShoppingCar.setSpu(goods.spu);
        addShoppingCar.setStoreSku(goods.storeSku);
        addShoppingCar.setItemColor(color);
        addShoppingCar.setItemSize(size);
        addShoppingCar.setItemName(goods.goodsName);
        addShoppingCar.setItemNum(commoditynumber);
        addShoppingCar.setItemPrice(goods.shopPrice);
        addShoppingCar.setItemPriceNow(goodsPrice);
        addShoppingCar.setItemModel("");
        addShoppingCar.setItemDiscount("0");
        addShoppingCar.setOrderDiscountTypeId("");
        addShoppingCar.setOrderDiscountType("");
        addShoppingCar.setItemDiscountRate("");

        String shoppingmsg = addShoppingCar.toString();// 邀请的成员的id的json数据
        map.put("shoppingmsg","[" + shoppingmsg + "]");

        event.setType(RequestEvent.ADDSHOPPINGCAR);
        event.setMap(map);
        event.setConsumer(addShoppingcarResult());
        EventBus.getDefault().post(event);
    }

    private DisposableObserver getDisposableObserver(){

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
                    Type objectType = new TypeToken<HttpResult<CommodityStock>>() {}.getType();
                    HttpResult<CommodityStock> httpResult2 = new Gson().fromJson(result, objectType);
                    price.setText("￥" + httpResult2.getContent().shopPrice);
                    goodsPrice = httpResult2.getContent().shopPrice;
                    stock.setText(getString(R.string.commodity_stock, httpResult2.getContent().stock));
                    commoditystock = httpResult2.getContent().stock;
                    sku = httpResult2.getContent().sku;
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

        return consumer;
    }

    private DisposableObserver addShoppingcarResult(){

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
                    Toast.makeText(mContext, "添加购物车成功", Toast.LENGTH_SHORT).show();
                    dismiss();
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

        return consumer;
    }

}
