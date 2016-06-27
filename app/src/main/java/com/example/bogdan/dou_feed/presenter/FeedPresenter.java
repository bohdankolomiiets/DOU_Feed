package com.example.bogdan.dou_feed.presenter;

import com.example.bogdan.dou_feed.model.entity.Category;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface FeedPresenter {

    void onCreateView();

    void loadFeed(boolean isRefresh);

    void onRefresh();

    void loadFeedByCaregory(String category, boolean isRefresh);

    void onCategoryClick();

    void onChangeCategory(Category category);
}
