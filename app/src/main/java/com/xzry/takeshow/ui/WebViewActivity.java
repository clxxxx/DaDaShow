package com.xzry.takeshow.ui;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 * Created by 周东阳 on 2017/9/11 0011.
 */

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.titlebar_view)
    TitleBarView mtitle;
    @BindView(R.id.webView)
    WebView webView;


    public static void initActivity(Context context, String imageLinkID){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("imageLinkID", imageLinkID);
        context.startActivity(intent);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webView.loadUrl(getIntent().getStringExtra("imageLinkID"));
        //设置Web视图
        webView.setWebViewClient(new webViewClient ());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mtitle.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
