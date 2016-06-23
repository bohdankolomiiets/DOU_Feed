package com.example.bogdan.dou_feed.presenter;

import android.os.Bundle;

import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.entity.ArticleEntity;
import com.example.bogdan.dou_feed.view.ArticleView;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class ArticlePresenterImpl extends BasePresenter implements ArticlePresenter {
    private ArticleView mView;
    private String mRubric;
    private String mUrl;

    @Inject
    public ArticlePresenterImpl(DouModel model, ArticleView view) {
        super(model);
        mView = view;
    }


    @Override
    public void onCreate(String rubric, String url) {
        mRubric = rubric;
        mUrl = url;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        mView.showLoading();
        mModel.getArticle(mRubric, mUrl)
                .subscribe(new Observer<ArticleEntity>() {
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
                    public void onNext(ArticleEntity articleEntity) {
                        if (articleEntity != null) {
                            showArticle(articleEntity);
                        }
                    }
                });
    }

    private void showArticle(ArticleEntity articleEntity) {
        mView.showHead(articleEntity.getAuthor(), articleEntity.getDate(), articleEntity.getTitle());
        for (int i = 0; i < articleEntity.size(); i++) {
            switch (articleEntity.getType(i)) {
                case CONTENT:
                    mView.showContent(articleEntity.getContent(i), false);
                    break;
                case EM_CONTENT:
                    mView.showContent(articleEntity.getContent(i), true);
                    break;
                case CONTENT_HEADING:
                    mView.showHeading(articleEntity.getContent(i));
                    break;
                case IMAGE:
                    mView.showImage(articleEntity.getContent(i));
                    break;
                case CONTENT_CODE:
                    mView.showCode(articleEntity.getContent(i));
                    break;
                case LINK:
                    mView.showLink(articleEntity.getContent(i));
                    break;
            }
        }
    }
}
