package com.xzry.takeshow.ui.homepager.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.SubscriberOnNextListener;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.base.NextDisposableObserver;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.PageEntity;
import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.homeEntity.ActivitiesEntity;
import com.xzry.takeshow.entity.homeEntity.HomeBannerEntity;
import com.xzry.takeshow.entity.homeEntity.HomeEntity;
import com.xzry.takeshow.entity.homeEntity.SpecialRecommondEntity;
import com.xzry.takeshow.ui.homepager.contract.HomeContract;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by 周东阳 on 2017/8/5 0005.
 */

public class HomePresenter extends BasePresenter implements HomeContract.Presenter{
    private DataManager mDataManager;

    private HomeContract.View mHomeView;
    @Inject
    public HomePresenter(DataManager mDataManager, HomeContract.View view) {
        this.mDataManager = mDataManager;
        this.mHomeView = view;

    }

    @Override
    public void getHomeData(int page) {
        //banner数据请求
        String bannerUrl = BaseValue.HOME_BANNER_URL;
        if (!TextUtils.isEmpty(BaseValue.TOKEN))
            bannerUrl = BaseValue.HOME_BANNER_URL + "/" +BaseValue.TOKEN;
        Disposable disposable = mDataManager.getRequestData(bannerUrl, new NextDisposableObserver(
                new SubscriberOnNextListener() {
            @Override
            public void onNext(String result) {
                Type objectType = new TypeToken<HttpResult<HomeBannerEntity>>() {}.getType();
                HttpResult<HomeBannerEntity> httpResult2 = new Gson().fromJson(result, objectType);
                HomeEntity homeEntity = new HomeEntity(HomeEntity.TYPE_TOP_BANNER);
                homeEntity.banner = httpResult2.getContent();
                mHomeView.setHomeData(0, homeEntity);
            }

            @Override
            public void onError(int code) {

            }
        }));
        addDisposabe(disposable);
        //精选好活动数据请求
        Disposable disposable2 = mDataManager.getRequestData(BaseValue.HOME_ACTIVITIES_URL, new NextDisposableObserver(
                new SubscriberOnNextListener() {
            @Override
            public void onNext(String result) {
                Type objectType = new TypeToken<HttpResult<List<ActivitiesEntity>>>() {}.getType();
                HttpResult<List<ActivitiesEntity>> httpResult2 = new Gson().fromJson(result, objectType);
                HomeEntity homeEntity = new HomeEntity(HomeEntity.TYPE_SELECTED_ACTIVITIES);
                homeEntity.activities = httpResult2.getContent();
                mHomeView.setHomeData(1, homeEntity);
            }

            @Override
            public void onError(int code) {

            }
        }));
        addDisposabe(disposable2);
        //每日特价数据请求
        Disposable disposable3 = mDataManager.getRequestData(BaseValue.HOME_SPECIALS_URL, new NextDisposableObserver(
                new SubscriberOnNextListener() {
            @Override
            public void onNext(String result) {
                Type objectType = new TypeToken<HttpResult<PageEntity<Goods>>>() {}.getType();
                HttpResult<PageEntity<Goods>> httpResult2 = new Gson().fromJson(result, objectType);
                HomeEntity homeEntity = new HomeEntity(HomeEntity.TYPE_DAILY_SPECIALS);
                homeEntity.specials = httpResult2.getContent();
                mHomeView.setHomeData(2, homeEntity);
            }

            @Override
            public void onError(int code) {

            }
        }));
        addDisposabe(disposable3);
        //专题推荐数据请求
        Disposable disposable4 = mDataManager.getRequestData(BaseValue.HOME_RECOMMOND_URL, new NextDisposableObserver(
                new SubscriberOnNextListener() {
            @Override
            public void onNext(String result) {
                Type objectType = new TypeToken<HttpResult<List<SpecialRecommondEntity>>>() {}.getType();
                HttpResult<List<SpecialRecommondEntity>> httpResult2 = new Gson().fromJson(result, objectType);
                HomeEntity homeEntity = new HomeEntity(HomeEntity.TYPE_SPECIAL_RECOMMOND);
                homeEntity.specialRecommond = httpResult2.getContent();
                mHomeView.setHomeData(3, homeEntity);
            }

            @Override
            public void onError(int code) {

            }
        }));
        addDisposabe(disposable4);
        //精选品牌数据请求
        Disposable disposable5 = mDataManager.getRequestData(BaseValue.HOME_HOTBRAND_URL, new NextDisposableObserver(
                new SubscriberOnNextListener() {
            @Override
            public void onNext(String result) {
                Type objectType = new TypeToken<HttpResult<List<HomeEntity.HotBrand>>>() {}.getType();
                HttpResult<List<HomeEntity.HotBrand>> httpResult2 = new Gson().fromJson(result, objectType);
                HomeEntity homeEntity = new HomeEntity(HomeEntity.TYPE_SELECT_THE_BRAND);
                homeEntity.hotbrand = httpResult2.getContent();
                mHomeView.setHomeData(4, homeEntity);
            }

            @Override
            public void onError(int code) {

            }
        }));
        addDisposabe(disposable5);
        Disposable disposable6 = mDataManager.getRequestData(BaseValue.HOME_HOTGOODS_URL+"/"+page, new NextDisposableObserver(
                new SubscriberOnNextListener() {
            @Override
            public void onNext(String result) {
                Type objectType = new TypeToken<HttpResult<PageEntity<Goods>>>() {}.getType();
                HttpResult<PageEntity<Goods>> httpResult2 = new Gson().fromJson(result, objectType);
                HomeEntity homeEntity = new HomeEntity(HomeEntity.TYPE_BOTTOM_CONTENT);
                homeEntity.hotgoods = httpResult2.getContent();
                mHomeView.setHomeData(5, homeEntity);
            }

            @Override
            public void onError(int code) {
                mHomeView.setHomeData(5, null);
            }
        }));
        addDisposabe(disposable6);
    }

}
