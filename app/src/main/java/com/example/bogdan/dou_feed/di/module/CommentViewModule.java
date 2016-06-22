package com.example.bogdan.dou_feed.di.module;

import com.example.bogdan.dou_feed.di.ActivityScope;
import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.presenter.CommentsPresenter;
import com.example.bogdan.dou_feed.presenter.CommentsPresenterImpl;
import com.example.bogdan.dou_feed.view.CommentsView;

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
    CommentsPresenter provideCommentsPresenter(DouModel model) {
        return new CommentsPresenterImpl(model, mView);
    }
}
