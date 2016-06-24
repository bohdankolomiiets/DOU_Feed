package com.example.bogdan.dou_feed.model.entity.feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 24.06.16
 */
public class FeedItemHeader {
    private String mImageUrl;
    private String mAuthorName;
    private String mDate;

    public FeedItemHeader(String imageUrl, String authorName, String date) {
        mImageUrl = imageUrl;
        mAuthorName = authorName;
        mDate = date;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getDate() {
        return mDate;
    }
}
