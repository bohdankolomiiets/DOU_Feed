package com.example.bogdan.dou_feed.model.entity;

import com.example.bogdan.dou_feed.model.entity.article.Article;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class Link implements Element {
    private String mUrl;
    private String mText;
    private Article.Type mType;

    public Link(String url, String text) {
        mType = Article.Type.LINK;
        mUrl = url;
        mText = text;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getText() {
        return mText;
    }

    @Override
    public Article.Type getmType() {
        return null;
    }
}
