package com.sxjs.common.model.http;

import android.content.Context;
import android.util.Log;

import com.sxjs.common.CommonConfig;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http helper负责创建ApiService实例
 */
@Singleton
public class HttpHelper {

    private static final String TAG = "HttpHelper";

    private Context context;
    private Retrofit mRetrofitClient;
    private HashMap<String, Object> mServiceMap;

    @Inject
    public HttpHelper(Context context) {
        this.context = context;
        mServiceMap = new HashMap<>();
        initRetrofitClient();
    }

    @SuppressWarnings("unchecked")
    public <S> S getService(Class<S> serviceClass) {
        if (mServiceMap.containsKey(serviceClass.getName())) {
            return (S) mServiceMap.get(serviceClass.getName());
        } else {
            Object obj = createService(serviceClass, null);
            mServiceMap.put(serviceClass.getName(), obj);
            return (S) obj;
        }


    }

    @SuppressWarnings("unchecked")
    public <S> S getService(Class<S> serviceClass, OkHttpClient client) {
        if (mServiceMap.containsKey(serviceClass.getName())) {
            return (S) mServiceMap.get(serviceClass.getName());
        } else {
            Object obj = createService(serviceClass, client);
            mServiceMap.put(serviceClass.getName(), obj);
            return (S) obj;
        }
    }

    private void initRetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .writeTimeout(CommonConfig.HTTP_READ_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(CommonConfig.HTTP_READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CommonConfig.HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d(TAG, "RetrofitUtil: " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        mRetrofitClient = createRetrofitClient(httpClient);
    }

    private Retrofit createRetrofitClient(OkHttpClient httpClient) {

        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(CommonConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    private <S> S createService(Class<S> serviceClass, OkHttpClient client){
        if(client == null){
            return mRetrofitClient.create(serviceClass);
        }else{
            return createRetrofitClient(client).create(serviceClass);
        }
    }

}
