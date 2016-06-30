package com.example.bogdan.dou_feed.di.module;

import android.support.annotation.NonNull;

import com.example.bogdan.dou_feed.di.ActivityScope;
import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.ui.comments.presenter.CommentsPresenter;
import com.example.bogdan.dou_feed.ui.comments.presenter.CommentsPresenterImpl;
import com.example.bogdan.dou_feed.ui.comments.view.CommentsView;
import com.example.bogdan.dou_feed.ui.feed.presenter.FeedPresenterImpl;
import com.example.bogdan.dou_feed.ui.feed.view.FeedView;
import com.example.bogdan.dou_feed.ui.lib.PresenterCache;
import com.example.bogdan.dou_feed.ui.lib.PresenterFactory;

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
        return cache.getPresenter(FeedView.class.getName(), new PresenterFactory<CommentsPresenter>() {
            @NonNull
            @Override
            public CommentsPresenterImpl createPresenter() {
                return new CommentsPresenterImpl(model, mView);
            }
        });
    }
}
