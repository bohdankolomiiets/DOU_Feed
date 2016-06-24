package com.example.bogdan.dou_feed.model.entity.feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 24.06.16
 */
public class Content {
    private String mTitle;
    private String mDescription;

    public Content(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
