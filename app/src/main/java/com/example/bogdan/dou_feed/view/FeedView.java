package com.example.bogdan.dou_feed.view;

import com.example.bogdan.dou_feed.model.entity.FeedItemEntity;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface FeedView {
    void showFeed(List<FeedItemEntity> feed);
}
