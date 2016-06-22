package com.example.bogdan.dou_feed.presenter;

import android.os.Bundle;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public interface CommentsPresenter {

    void onCreateView(Bundle savedInstanceState);

    void loadComments(String rubric, String pageUrl);

    void onRefresh();
}
