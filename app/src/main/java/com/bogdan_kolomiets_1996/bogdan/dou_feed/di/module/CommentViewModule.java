package com.bogdan_kolomiets_1996.bogdan.dou_feed.di.module;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.ActivityScope;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.DouModel;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.comments.presenter.CommentsPresenter;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.comments.presenter.CommentsPresenterImpl;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.comments.view.CommentsView;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.lib.PresenterCache;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
@Module
public class CommentViewModule {
    private CommentsView mView;

    public CommentViewModule(CommentsView view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    CommentsPresenter provideCommentsPresenter(DouModel model, PresenterCache cache) {
        return new CommentsPresenterImpl(model, mView);
    }
}
