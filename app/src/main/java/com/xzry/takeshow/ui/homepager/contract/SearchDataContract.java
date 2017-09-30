package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.request.SearchRequest;
import com.xzry.takeshow.entity.response.SearchDataResponse;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */


public interface SearchDataContract {
    interface View {
        void getSearchData(SearchDataResponse dada);
    }

    interface Presenter {
        void toSearch(SearchRequest object);
    }
}
