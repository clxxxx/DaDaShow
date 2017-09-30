package com.xzry.takeshow.ui.homepager.contract;

import com.xzry.takeshow.entity.CategoryEntity;

import java.util.List;

/**
 * Created by 周东阳 on 2017/9/6 0006.
 */

public interface AllCCContract {

    interface View {
        void setFirstData(List<CategoryEntity> list);
        void setSecondData(List<CategoryEntity> list);
    }

    interface Presenter {
        void getData();
        void getSecondData(String id);
    }
}
