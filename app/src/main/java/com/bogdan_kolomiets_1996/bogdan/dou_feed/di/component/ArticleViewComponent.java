package com.bogdan_kolomiets_1996.bogdan.dou_feed.di.component;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.ActivityScope;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.module.ArticleViewModule;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.view.ArticleFragment;

import dagger.Subcomponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
@ActivityScope
@Subcomponent(modules = {ArticleViewModule.class})
public interface ArticleViewComponent {
    void inject(ArticleFragment articleFragment);
}
