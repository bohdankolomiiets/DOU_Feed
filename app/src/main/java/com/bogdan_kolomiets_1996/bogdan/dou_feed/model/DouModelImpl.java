package com.bogdan_kolomiets_1996.bogdan.dou_feed.model;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.Category;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.article.Article;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.CommentItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.api.DouApi;

import java.util.ArrayList;
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
    public Observable<Article> getArticle(String rubric, String articleUrl) {
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

    @Override
    public Observable<List<Category>> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Все рубрики", ""));
        categories.add(new Category("Статьи", "articles"));
        categories.add(new Category("Интервью", "interviews"));
        categories.add(new Category("Колумнисты", "columns"));
        categories.add(new Category("Новости", "news"));
        categories.add(new Category("Ссылки", "digests"));
        categories.add(new Category("События", "events"));
        categories.add(new Category("Новости сайта", "sitenews"));

        return Observable.from(categories)
                .toList()
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) mSchedulerTransformer;
    }
}
