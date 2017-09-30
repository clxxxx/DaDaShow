package com.xzry.takeshow.ui.homepager.contract.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.ui.homepager.SearchHomeActivity;
import com.xzry.takeshow.ui.homepager.module.SearchModule;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-6
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchHomeActivity activity);
}
