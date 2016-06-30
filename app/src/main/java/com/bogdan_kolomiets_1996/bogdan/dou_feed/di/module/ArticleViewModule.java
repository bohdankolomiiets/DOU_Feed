package com.bogdan_kolomiets_1996.bogdan.dou_feed.di.module;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.ActivityScope;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.DouModel;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.presenter.ArticlePresenter;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.presenter.ArticlePresenterImpl;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.view.ArticleView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
@Module
public class ArticleViewModule {
    private ArticleView mView;

    public ArticleViewModule(ArticleView view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    ArticlePresenter provideArticlePresenter(DouModel model) {
        return new ArticlePresenterImpl(model, mView);
    }
}
