package com.example.bogdan.dou_feed.view;

import com.example.bogdan.dou_feed.model.entity.ArticleEntity;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class ArticleFragment extends BaseFragment implements ArticleView {

    @Override
    public void showArticle(ArticleEntity article) {

    }

    @Override
    public void showLoading() {
        showProgressDial();
    }

    @Override
    public void hideLoading() {
        hideProgressDial();
    }

    @Override
    public void showError(String message) {
        onError(message);
    }
}
