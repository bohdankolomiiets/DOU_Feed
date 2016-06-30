package com.bogdan_kolomiets_1996.bogdan.dou_feed.di.component;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.ActivityScope;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.module.FeedViewModule;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.view.FeedFragment;

import dagger.Subcomponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
@ActivityScope
@Subcomponent(modules = {FeedViewModule.class})
public interface FeedViewComponent {
    void inject(FeedFragment feedFragment);
}
