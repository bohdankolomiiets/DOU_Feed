package com.example.bogdan.dou_feed.model.entity;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 27.06.16
 */
public class Category {
    private String mName;
    private String mUrlName;

    public Category(String name, String url) {
        mName = name;
        mUrlName = url;
    }

    public String getName() {
        return mName;
    }

    public String getUrl() {
        return mUrlName;
    }
}
