package com.example.bogdan.dou_feed.presenter;

import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.entity.FeedItemEntity;
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
        loadFeed();
    }

    @Override
    public void loadFeed() {
        mModel.getFeed(++pageNumber)
                .subscribe(new Observer<List<FeedItemEntity>>() {

                    @Override
                    public void onCompleted() {
                        mView.stopRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<FeedItemEntity> feedItemEntities) {
                        if (feedItemEntities != null) {
                            mView.showFeed(feedItemEntities);
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        pageNumber = 0;
        loadFeed();
    }

}
