package com.example.bogdan.dou_feed.ui.comments.presenter;

import android.os.Bundle;

import com.example.bogdan.dou_feed.HTTPUtils;
import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.entity.CommentItem;
import com.example.bogdan.dou_feed.ui.common.BasePresenter;
import com.example.bogdan.dou_feed.ui.comments.view.CommentsView;

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
    private String mCategory;
    private String mUrl;

    @Inject
    public CommentsPresenterImpl(DouModel model, CommentsView view) {
        super(model);
        mView = view;
    }


    @Override
    public void onCreate(String rubric, String url) {
        mUrl = url;
        mCategory = rubric;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        loadComments(mCategory, mUrl, true);
    }

    @Override
    public void loadComments(String rubric, String pageUrl, boolean showLoading) {
        showNetworkError();
        if (showLoading)
            mView.showLoading();
        mModel.getComments(rubric, pageUrl)
                .subscribe(new Observer<List<CommentItem>>() {
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
                    public void onNext(List<CommentItem> commentItemEntities) {
                        if (isNotEmpty(commentItemEntities)) {
                            mView.showComments(commentItemEntities);
                        } else {
                            mView.showEmptyList();
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        loadComments(mCategory, mUrl, false);
    }

    private boolean isNotEmpty(List<CommentItem> comments) {
        return (comments != null && !comments.isEmpty());
    }

    private void showNetworkError() {
        mView.showError("Проверьте, пожалуйста, интернет соединение");
    }
}
