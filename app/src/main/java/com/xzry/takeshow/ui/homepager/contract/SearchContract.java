package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.request.SearchRequest;
import com.xzry.takeshow.entity.response.SearchDataResponse;

import java.util.List;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */


public interface SearchContract {
    interface View {
        void getSearchData(SearchDataResponse dada);
        void getHistoryData(List<String> itemList);
        void getClearHistoryData(String str);
    }

    interface Presenter {
        void toSearch(SearchRequest obj);
        void toHistoryData(String userid);
        void clearHistoryData(String userid);
    }
}
