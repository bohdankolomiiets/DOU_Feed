package com.example.bogdan.dou_feed.di;

import com.example.bogdan.dou_feed.view.MainActivity;

import dagger.Component;
import dagger.Subcomponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
@ActivityScope
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
