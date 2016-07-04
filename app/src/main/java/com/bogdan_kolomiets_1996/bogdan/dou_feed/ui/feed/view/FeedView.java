package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.view;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.common.View;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface FeedView extends View {
  void showFeed(List<FeedItem> feed);

  void stopRefresh();

  void addNewArticle();

}
