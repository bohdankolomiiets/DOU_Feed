package com.example.bogdan.dou_feed.presenter;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface FeedPresenter {

    void onCreateView();

    void loadFeed(boolean isRefresh);

    void onRefresh();
}
