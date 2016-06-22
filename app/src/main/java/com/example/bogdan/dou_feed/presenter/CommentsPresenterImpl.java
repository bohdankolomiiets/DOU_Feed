package com.example.bogdan.dou_feed.presenter;

import android.os.Bundle;

import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.entity.CommentItemEntity;
import com.example.bogdan.dou_feed.view.CommentsView;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class CommentsPresenterImpl extends BasePresenter implements CommentsPresenter {
    private CommentsView mView;

    @Inject
    public CommentsPresenterImpl(DouModel model, CommentsView view) {
        super(model);
        mView = view;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        String rubric = savedInstanceState.getString("rubric");
        String pageUrl = savedInstanceState.getString("pageUrl");
        loadComments(rubric, pageUrl);
    }

    @Override
    public void loadComments(String rubric, String pageUrl) {
        mView.showLoading();
        mModel.getComments(rubric, pageUrl)
                .subscribe(new Observer<List<CommentItemEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<CommentItemEntity> commentItemEntities) {
                        if (commentItemEntities != null) {
                            mView.showComments(commentItemEntities);
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {

    }
}
