package com.example.bogdan.dou_feed.di.module;

import com.example.bogdan.dou_feed.di.ActivityScope;
import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.presenter.ArticlePresenter;
import com.example.bogdan.dou_feed.presenter.ArticlePresenterImpl;
import com.example.bogdan.dou_feed.view.ArticleView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
@Module
public class ArticleModule {
    private ArticleView mView;

    public ArticleModule(ArticleView view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    ArticlePresenter provideArticlePresenter(DouModel model) {
        return new ArticlePresenterImpl(model, mView);
    }
}
