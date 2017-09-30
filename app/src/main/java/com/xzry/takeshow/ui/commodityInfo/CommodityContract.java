package com.xzry.takeshow.ui.commodityInfo;

import com.xzry.takeshow.entity.commodity.Goods;
import com.xzry.takeshow.entity.homeEntity.HomeEntity;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by 周东阳 on 2017/9/2 0002.
 */

public interface CommodityContract {

    interface View {
        void setData(Goods goods);
    }

    interface Presenter {
        void getData(Map<String, String> map);
        void checkStock(Map<String, String> map, DisposableObserver consumer);
        void collect(Map<String, String> map, DisposableObserver consumer);
        void addShoppingcar(Map<String, String> map, DisposableObserver consumer);
    }
}
