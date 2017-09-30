package com.xzry.takeshow.ui.homepager.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.common.model.DataManager;
import com.xzry.takeshow.base.BasePresenter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.HttpResult;
import com.xzry.takeshow.entity.request.SearchRequest;
import com.xzry.takeshow.entity.response.SearchDataResponse;
import com.xzry.takeshow.ui.homepager.contract.SearchDataContract;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */


public class SearchDataPresenter extends BasePresenter implements SearchDataContract.Presenter {
    private DataManager mDataManager;

    private SearchDataContract.View view;
    @Inject
    public SearchDataPresenter(DataManager mDataManager, SearchDataContract.View view) {
        this.mDataManager = mDataManager;
        this.view = view;

    }

    @Override
    public void toSearch(SearchRequest obj) {
        Disposable disposable = mDataManager.postRequestData(BaseValue.SEARCH_DATA_URL,obj,new ErrorDisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {

                try {
                    String string = responseBody.string();
                    HttpResult<SearchDataResponse> result = new Gson().fromJson(string, new TypeToken<HttpResult<SearchDataResponse>>() {}.getType());
                    if (result.getState().equals("success")) {
                        SearchDataResponse dadaList = result.getContent();
                        view.getSearchData(dadaList);
                    }else {

                    }







//                    HttpResult<SearchDataResponse> httpResult = new Gson().fromJson(string, HttpResult.class);
//                    Type objectType = new TypeToken<HttpResult<SearchDataResponse>>() {}.getType();
//                    HttpResult<SearchDataResponse> respon = new Gson().fromJson(string, objectType);
//                    SearchDataResponse dadaList2 = result.getContent();
//                    if (httpResult.getState().equals("success")) {
//                        Log.i("sear","---success2------ddlist:");
//                        Log.i("sear","---------ddlist2:"+dadaList2.curPage);
//                        //view.showToast(1);
//                    }else  {
//
//                        //view.showToast(0);
//                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onComplete() {

            }
        });
        addDisposabe(disposable);
    }
}
