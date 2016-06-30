package com.example.bogdan.dou_feed.ui.feed.presenter;

import com.example.bogdan.dou_feed.lib.Presenter;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface FeedPresenter extends Presenter {

  void onCreateView();

  void loadFeed(boolean isRefresh);

  void onRefresh();
}
