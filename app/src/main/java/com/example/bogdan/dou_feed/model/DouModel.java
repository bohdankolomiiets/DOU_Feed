package com.example.bogdan.dou_feed.model;

import com.example.bogdan.dou_feed.FeedItemEntity;

import java.util.List;

import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface DouModel {

    Observable<List<FeedItemEntity>> getFeedByRubric(String rubric, int pageNumber);

    Observable<List<FeedItemEntity>> getFeed(int pageNumber);
}
