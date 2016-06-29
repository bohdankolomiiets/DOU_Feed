package com.example.bogdan.dou_feed.ui.feed.presenter;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.HTTPUtils;
import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.example.bogdan.dou_feed.ui.common.BasePresenter;
import com.example.bogdan.dou_feed.ui.feed.view.FeedView;

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
        showNetworkError();
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
                        e.printStackTrace();
                        mView.hideLoading();

                        if (HTTPUtils.isNetworkException(e)) {
                            showNetworkError();
                        }
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

    private void showNetworkError() {
        mView.showError("Проверьте, пожалуйста, интернет соединение");
    }

}
