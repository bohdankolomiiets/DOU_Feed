package com.example.bogdan.dou_feed.ui.article.presenter;

import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public interface ArticlePresenter {

    void onCreate(String rubric, String url);

    void onCreateView(Bundle savedInstanceState);

}
