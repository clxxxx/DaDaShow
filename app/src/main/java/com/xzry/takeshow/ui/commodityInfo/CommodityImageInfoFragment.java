package com.xzry.takeshow.ui.commodityInfo;

import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sxjs.common.base.BaseFragment;
import com.xzry.takeshow.R;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/8/9 0009.
 */

public class CommodityImageInfoFragment extends BaseFragment {

//    @BindView(R.id.commodity_parameter)
//    RecyclerView commodity_parameter;
//    @BindView(R.id.commodity_img)
//    ListView commodity_img;

    @BindView(R.id.commodity_webview)
    WebView commodity_webview;


    public static CommodityImageInfoFragment newInstance() {
        return new CommodityImageInfoFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_commodity_imageinfo;
    }

    @Override
    protected void initView() {

        commodity_webview.getSettings().setJavaScriptEnabled(true);
        commodity_webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        commodity_webview.setHorizontalScrollBarEnabled(false);
        commodity_webview.getSettings().setSupportZoom(false);
        commodity_webview.getSettings().setBuiltInZoomControls(true);
        commodity_webview.setInitialScale(130);
        commodity_webview.setHorizontalScrollbarOverlay(true);
        commodity_webview.setWebChromeClient(new WebChromeClient());
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scale = dm.densityDpi;
        if (scale == 240) { //
            commodity_webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (scale == 160) {
            commodity_webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else {
            commodity_webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public void setData(String html){
        commodity_webview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }
}
