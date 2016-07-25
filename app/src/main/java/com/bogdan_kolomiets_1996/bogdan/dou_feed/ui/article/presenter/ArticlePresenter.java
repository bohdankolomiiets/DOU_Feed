package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.presenter;

import android.os.Bundle;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public interface ArticlePresenter {

    void onCreate(String rubric, String url);

    void onCreateView(Bundle savedInstanceState);

    void onShareClick();

}
