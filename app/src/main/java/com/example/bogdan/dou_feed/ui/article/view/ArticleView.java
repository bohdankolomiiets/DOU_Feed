package com.example.bogdan.dou_feed.ui.article.view;

import android.graphics.Bitmap;

import com.example.bogdan.dou_feed.model.entity.page.PageElement;
import com.example.bogdan.dou_feed.ui.common.View;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public interface ArticleView extends View {
    String RUBRIC_KEY = "RUBRIC_KEY";

    String URL_KEY = "URL_KEY";

    void showPageElement(PageElement element);

    void showHead(String author, String date, String title);
}
