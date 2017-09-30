package com.xzry.takeshow.wxapi;

import android.content.Intent;
import android.util.Log;

import com.sxjs.common.base.BaseActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xzry.takeshow.R;

/**
 * Created by 周东阳 on 2017/9/18 0018.
 */

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public int getLayout() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected void initView() {
        api = WXAPIFactory.createWXAPI(this, "wxaa803e487961ae06");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
//        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
//
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
//        }

        int code= resp.errCode;
        if (code == 0){
            //显示充值成功的页面和需要的操作
            showShortToast("充值成功");
            showShortToast(resp.errStr);
            showShortToast(resp.transaction);

            Log.i("resp.errStr",resp.errStr);
            Log.i("resp.errCode",resp.errCode+"");
            Log.i("resp.transaction",resp.transaction);
        }

        if (code == -1){
            //错误
            showShortToast("充值失败");
            showShortToast(resp.errStr);
            showShortToast(resp.transaction);

            Log.i("resp.errStr",resp.errStr);
            Log.i("resp.errCode",resp.errCode+"");
            Log.i("resp.transaction",resp.transaction);
        }

        if (code == -2){
            //用户取消
            showShortToast("用户取消");
            showShortToast(resp.errStr);
            showShortToast(resp.transaction);

            Log.i("resp.errStr",resp.errStr);
            Log.i("resp.errCode",resp.errCode+"");
            Log.i("resp.transaction",resp.transaction);
        }
    }
}