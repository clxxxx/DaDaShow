package com.xzry.takeshow.ui.mine.contract;

import com.xzry.takeshow.entity.FootMarkEntity;

import java.util.Map;

/**
 * @author: luosy
 * @date: 2017-9-23
 * @description:
 */


public interface FootMarkContract {
    interface View {

        void getListSuccess(FootMarkEntity entity);
        void getDeleteSuccess();
    }

    interface Presenter {
        void getFootMarkList(Map map);
        void getDetele(Map map);
    }
}
