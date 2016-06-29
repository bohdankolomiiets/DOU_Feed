package com.example.bogdan.dou_feed.view;

import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface FeedView extends View {
  void showFeed(List<FeedItem> feed);

  void stopRefresh();

}
