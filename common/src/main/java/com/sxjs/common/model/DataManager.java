package com.sxjs.common.model;

import android.content.Context;

import com.google.gson.Gson;
import com.sxjs.common.model.dao.DataBaseHelper;
import com.sxjs.common.model.http.BaseApiService;
import com.sxjs.common.model.http.HttpHelper;
import com.sxjs.common.model.sp.SharePreferenceHelper;
import com.sxjs.common.util.FillUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2017/3/9.
 */
@Singleton
public class DataManager {

    private HttpHelper httpHelper;

    private SharePreferenceHelper sharePreferenceHelper;

    private DataBaseHelper dataBaseHelper;

    private Context context;

    @Inject
    public DataManager( Context context ,HttpHelper httpHelper , SharePreferenceHelper sharePreferenceHelper
        , DataBaseHelper dataBaseHelper) {
        this.context = context;
        this.httpHelper = httpHelper;
        this.sharePreferenceHelper = sharePreferenceHelper;
        this.dataBaseHelper = dataBaseHelper;
    }

    public Disposable getRequestData(String url,  DisposableObserver<ResponseBody> consumer){
        return changeIOToMainThread(httpHelper.getService(BaseApiService.class).executeGet(url),consumer);
    }

    public Disposable getRequestData(String url, Map map,  DisposableObserver<ResponseBody> consumer){
        return changeIOToMainThread(httpHelper.getService(BaseApiService.class).executeGet(url,map),consumer);
    }

    public Disposable postRequestData(String url , Object object, DisposableObserver<ResponseBody> consumer){
        return changeIOToMainThread(httpHelper.getService(BaseApiService.class).executePost(url,object),consumer);
    }


    public Disposable postRequestData(String url,Map map, DisposableObserver<ResponseBody> consumer){
        return changeIOToMainThread(httpHelper.getService(BaseApiService.class).executePost(url,map),consumer);
    }
    public Disposable postRequestData(String url,Map map, MultipartBody.Part part, DisposableObserver<ResponseBody> consumer){
        return changeIOToMainThread(httpHelper.getService(BaseApiService.class).executePost(url,map,part),consumer);
    }

    private Disposable changeIOToMainThread(Observable<ResponseBody> observable ,DisposableObserver<ResponseBody> consumer ){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(consumer);
    }

    public void saveSPData(String key , String value){
        sharePreferenceHelper.saveData(key , value);
    }

    public void saveSPMapData(Map<String,String> map){
        sharePreferenceHelper.saveData(map);
    }

    public String getSPData(String key){
        return sharePreferenceHelper.getValue(key);
    }

    public void deleteSPData(){
        sharePreferenceHelper.deletePreference();
    }

    public Map<String ,String> getSPMapData(){
        return sharePreferenceHelper.readData();
    }

    public List<String> getTypeOfNameData(){
        ArrayList<String> list = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            list.add("家用电器");
        }
        return list;
    }

    public<S> Disposable getData(DisposableObserver<S> consumer , final Class<S> clazz , final String fillName) {
        return Observable.create(new ObservableOnSubscribe<S>() {
            @Override
            public void subscribe(ObservableEmitter<S> e) throws Exception {
                InputStream is = context.getAssets().open(fillName);
                String text = FillUtil.readTextFromFile(is);
                Gson gson = new Gson();
                e.onNext(gson.fromJson(text, clazz));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(consumer);
    }
}
