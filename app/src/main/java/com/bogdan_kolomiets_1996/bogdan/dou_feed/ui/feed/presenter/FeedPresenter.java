package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.presenter;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.lib.Presenter;

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
