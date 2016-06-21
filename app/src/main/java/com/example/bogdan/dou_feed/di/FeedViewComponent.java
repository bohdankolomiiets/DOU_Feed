package com.example.bogdan.dou_feed.di;

import com.example.bogdan.dou_feed.view.FeedFragment;
import com.example.bogdan.dou_feed.view.MainActivity;

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
