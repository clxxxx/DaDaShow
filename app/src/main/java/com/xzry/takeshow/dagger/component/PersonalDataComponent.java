package com.xzry.takeshow.dagger.component;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerActivity;
import com.xzry.takeshow.dagger.module.PersonalDataModule;
import com.xzry.takeshow.ui.mine.NickNameEditActivity;
import com.xzry.takeshow.ui.mine.PersonalDataActivity;

import dagger.Component;

/**
 * @author: luosy
 * @date: 2017-9-19
 * @description:
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = PersonalDataModule.class)
public interface PersonalDataComponent {
    void inject(PersonalDataActivity activity);
    void inject(NickNameEditActivity activity);
}

