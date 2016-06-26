package com.example.bogdan.dou_feed.presenter;

import android.os.Bundle;

import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.entity.Link;
import com.example.bogdan.dou_feed.model.entity.article.Article;
import com.example.bogdan.dou_feed.model.entity.TableEntity;
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
                .subscribe(new Observer<Article>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideLoading();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Article articleEntity) {
                        if (articleEntity != null) {
                            showArticle(articleEntity);
                        }
                    }
                });
    }

    private void showArticle(Article articleEntity) {
        mView.showHead(articleEntity.getHeader().getAuthorName(),
                articleEntity.getHeader().getDate(),
                articleEntity.getHeader().getTitle());
        for (int i = 0; i < articleEntity.size(); i++) {
            switch (articleEntity.getType(i)) {
                case CONTENT:
                    mView.showContent(articleEntity.getContent(i));
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
                    break;
                case BLOCKQUOTE:
                    mView.showBlockquote(articleEntity.getContent(i));
                    break;
                case TABLE:
                    mView.showTable((TableEntity)articleEntity.getElement(i));
                    break;
                case LIST_ELEMENT:
                    mView.showListElement(articleEntity.getContent(i));
                    break;
            }
        }
    }
}
