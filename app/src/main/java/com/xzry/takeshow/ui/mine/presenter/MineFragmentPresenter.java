package com.xzry.takeshow.ui.mine.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.umeng.socialize.utils.Log;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.UserData;
import com.xzry.takeshow.entity.UserInfo;
import com.xzry.takeshow.ui.mine.contract.MineFragmentContract;
import com.xzry.takeshow.utils.SpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

import static com.xzry.takeshow.base.BaseValue.User.HEADIMG;
import static com.xzry.takeshow.base.BaseValue.User.MARTURL;
import static com.xzry.takeshow.base.BaseValue.User.MOBILE;
import static com.xzry.takeshow.base.BaseValue.User.NICKNAME;
import static com.xzry.takeshow.base.BaseValue.User.PERSONALITY;
import static com.xzry.takeshow.base.BaseValue.User.SEX;
import static com.xzry.takeshow.base.BaseValue.User.VISITCOUNT;

/**
 * @author: luosy
 * @date: 2017-9-19
 * @description:
 */


public class MineFragmentPresenter extends BasePresenter implements MineFragmentContract.Presenter{
    String TAG = "mine";
    private DataManager mDataManager;
    private MineFragmentContract.View view;
    @Inject
    public MineFragmentPresenter(DataManager dataManager, MineFragmentContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }

    public void toGetAll(String token) {
        Map map = new HashMap();
        map.put("token",token);
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_MINE_DATA_URL,map, new ErrorDisposableObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"-----个人资料----------"+string);
                HttpResult result = new Gson().fromJson(string,HttpResult.class);
                if (TextUtils.equals(result.getState(),"success")) {
                    if (TextUtils.equals(result.getCode(),"13000")) {
                       HttpResult<UserData> result2 = new Gson().fromJson(string,new TypeToken<HttpResult<UserData>>(){}.getType());
                       view.resultData(result2.getContent());
                    }
                }else {

                }

            }
            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }
    /**
     * 保存用户数据
     */
    public static void keepUserData(UserInfo userInfo) {
        Log.i("login","登录成功------id:"+userInfo.id+"--token:"+userInfo.code);
        BaseValue.ISLOGIN = true;
        BaseValue.USER_ID = userInfo.id;
        BaseValue.TOKEN = userInfo.code;

        SpUtil.getInstance().putString(MOBILE, userInfo.mobile);                  //手机号
        SpUtil.getInstance().putString(NICKNAME, userInfo.nickname);                 //昵称
        SpUtil.getInstance().putInt(SEX, userInfo.sex);                        //性别
        SpUtil.getInstance().putString(PERSONALITY, userInfo.personality);        //个性描述
        SpUtil.getInstance().putString(HEADIMG, userInfo.headerUrl);              //头像
        SpUtil.getInstance().putString(MARTURL, userInfo.martUrl);                 //二维码

        SpUtil.getInstance().putString(VISITCOUNT, userInfo.visitCount);          //登录次数



    }
}
