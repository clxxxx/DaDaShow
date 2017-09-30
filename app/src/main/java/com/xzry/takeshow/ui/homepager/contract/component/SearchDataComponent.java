package com.xzry.takeshow.ui.homepager.contract.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.ui.homepager.SearchDataActivity;
import com.xzry.takeshow.ui.homepager.module.SearchDataModule;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */
@PerActivity
@Component(dependencies = AppComponent.class , modules = SearchDataModule.class)
public interface SearchDataComponent {
    void inject(SearchDataActivity activity);
}
