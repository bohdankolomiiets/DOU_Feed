package com.example.bogdan.dou_feed.view;

import com.example.bogdan.dou_feed.model.entity.ArticleEntity;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public interface ArticleView extends View {

    void showArticle(ArticleEntity article);

}
