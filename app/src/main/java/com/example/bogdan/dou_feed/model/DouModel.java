package com.example.bogdan.dou_feed.model;

import com.example.bogdan.dou_feed.model.entity.Article;
import com.example.bogdan.dou_feed.model.entity.CommentItem;
import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;

import java.util.List;

import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface DouModel {

    Observable<List<FeedItem>> getFeedByRubric(String rubric, int pageNumber);

    Observable<List<FeedItem>> getFeed(int pageNumber);

    Observable<Article> getArticle(String rubric, String articleUrl);

    Observable<List<CommentItem>> getComments(String rubric, String articleUrl);
}
