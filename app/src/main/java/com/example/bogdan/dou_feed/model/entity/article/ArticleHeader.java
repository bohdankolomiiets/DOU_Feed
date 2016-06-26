package com.example.bogdan.dou_feed.model.entity.article;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class ArticleHeader {
    private String mAuthorName;
    private String mDate;
    private String mTitle;

    public ArticleHeader(String authorName, String date, String title) {
        mAuthorName = authorName;
        mDate = date;
        mTitle = title;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }
}
