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
import com.xzry.takeshow.entity.HttpResultImg;
import com.xzry.takeshow.entity.UserData;
import com.xzry.takeshow.ui.mine.contract.PersonalDataContract;
import com.xzry.takeshow.utils.SpUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static com.xzry.takeshow.base.BaseValue.User.NICKNAME;

/**
 * @author: luosy
 * @date: 2017-9-19
 * @description:
 */


public class PersonalDataPresenter extends BasePresenter implements PersonalDataContract.Presenter{
    String TAG = "address";
    private DataManager mDataManager;
    private PersonalDataContract.View view;
    @Inject
    public PersonalDataPresenter(DataManager dataManager, PersonalDataContract.View view){
        this.mDataManager = dataManager;
        this.view = view;
    }
    //查询所有个人信息
    public void getAllData(String token){
        Map map = new HashMap();
        map.put("token",token);
        Disposable disposable = mDataManager.postRequestData(BaseValue.GET_PERSONAL_CENTER_URL,map, new ErrorDisposableObserver<ResponseBody>() {

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
    //去修改头像
    public void toModifyHead(String token, String path){
        Map<String, RequestBody> params = new HashMap<>();
        params.put("token",  RequestBody.create(MediaType.parse("text/plain"), token));
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jepg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
        Disposable disposable = mDataManager.postRequestData(BaseValue.MODIFY_THE_HEAD_URL,params,part, new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                String string = null;
                try {
                    string = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HttpResultImg result = new Gson().fromJson(string,HttpResultImg.class);
                if (TextUtils.equals(result.state,"success")) {
                    if (TextUtils.equals(result.code,"13000")) {
                        view.resultHeadImgData(result.content.headerUrl);

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
     *  去修改昵称
     */
    public void toModifyNickName(String token, final String name){
        Map map = new HashMap();
        map.put("token",token);
        map.put("nickname",name);
        Disposable disposable = mDataManager.postRequestData(BaseValue.MODIFY_NICKNAME_URL,map, new ErrorDisposableObserver<ResponseBody>() {

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
                        view.resultNickNameData(name);
                        SpUtil.getInstance().putString(NICKNAME, name);                 //昵称
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
}
