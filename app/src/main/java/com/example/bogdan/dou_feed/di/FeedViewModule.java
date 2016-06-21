package com.example.bogdan.dou_feed.di;

import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.presenter.FeedPresenter;
import com.example.bogdan.dou_feed.presenter.FeedPresenterImpl;
import com.example.bogdan.dou_feed.view.FeedView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
@Module
public class FeedViewModule {
    private FeedView mView;

    public FeedViewModule(FeedView view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    FeedPresenter provideFeedPresenter(DouModel model) {
        return new FeedPresenterImpl(model, mView);
    }

}
