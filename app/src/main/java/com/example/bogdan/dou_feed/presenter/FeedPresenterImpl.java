package com.example.bogdan.dou_feed.presenter;

import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.example.bogdan.dou_feed.view.FeedView;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class FeedPresenterImpl extends BasePresenter implements FeedPresenter {
    private FeedView mView;
    private int pageNumber = 0;

    @Inject
    public FeedPresenterImpl(DouModel model, FeedView view) {
        super(model);
        mView = view;
    }

    @Override
    public void onCreateView() {
        loadFeed(false);
    }

    @Override
    public void loadFeed(boolean isRefresh) {
        if (!isRefresh) {
            mView.showLoading();
        }
        mModel.getFeed(++pageNumber)
                .subscribe(new Observer<List<FeedItem>>() {

                    @Override
                    public void onCompleted() {
                        mView.stopRefresh();
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<FeedItem> feedItemEntities) {
                        if (feedItemEntities != null) {
                            mView.showFeed(feedItemEntities);
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        pageNumber = 0;
        loadFeed(true);
    }

}
