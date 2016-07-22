package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.presenter;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.Constants;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.DouApp;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.HTTPUtils;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.DouModel;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.common.BasePresenter;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.view.FeedView;

import java.util.ArrayList;
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
    private List<FeedItem> mFeed;

    @Inject
    public FeedPresenterImpl(DouModel model, FeedView view) {
        super(model);
        mView = view;
        mFeed = new ArrayList<>();
    }

    @Override
    public void onCreateView() {
        if (mFeed.isEmpty()) {
            loadFeed(false);
        } else {
            mView.showFeed(mFeed);
        }
    }

    @Override
    public void loadFeed(boolean isRefresh) {
        if (DouApp.isNetworkAvailable() && !isRefresh) {
            mView.showLoading();
        } else if (isRefresh && !DouApp.isNetworkAvailable()) {
            mView.showError(Constants.HTTP.NET_ERROR_MSG);
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
                            mView.showError(Constants.HTTP.NET_ERROR_MSG);                        }
                    }

                    @Override
                    public void onNext(List<FeedItem> feedItemEntities) {
                        if (feedItemEntities != null) {
                            mFeed.addAll(feedItemEntities);
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

    @Override
    public void onAddArticleClick() {
        mView.addNewArticle();
    }
}
