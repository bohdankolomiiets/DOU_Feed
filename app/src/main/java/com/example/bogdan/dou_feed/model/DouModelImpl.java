package com.example.bogdan.dou_feed.model;

import com.example.bogdan.dou_feed.model.entity.ArticleEntity;
import com.example.bogdan.dou_feed.model.entity.CommentItem;
import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.example.bogdan.dou_feed.api.DouApi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
@Singleton
public class DouModelImpl implements DouModel {
    private final Observable.Transformer mSchedulerTransformer;
    private final DouApi mApiInterface;

    @Inject
    public DouModelImpl(Observable.Transformer schedulerTransformer, DouApi api) {
        mSchedulerTransformer = schedulerTransformer;
        mApiInterface = api;
    }

    @Override
    public Observable<List<FeedItem>> getFeedByRubric(String rubric, int pageNumber) {
        return mApiInterface
                .getFeedEntityByRubric(rubric, pageNumber)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<FeedItem>> getFeed(int pageNumber) {
        return mApiInterface
                .getFeedEntity(pageNumber)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ArticleEntity> getArticle(String rubric, String articleUrl) {
        return mApiInterface
                .getArticleEntity(rubric, articleUrl)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<CommentItem>> getComments(String rubric, String articleUrl) {
        return mApiInterface
                .getCommentListEntity(rubric, articleUrl)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) mSchedulerTransformer;
    }
}
