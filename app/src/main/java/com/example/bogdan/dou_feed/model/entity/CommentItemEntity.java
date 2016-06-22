package com.example.bogdan.dou_feed.model.entity;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class CommentItemEntity {
    private String mImageUrl;
    private String mAuthorName;
    private String mDateOfPublication;

    public CommentItemEntity(String imageUrl, String authorName, String date) {
        mImageUrl = imageUrl;
        mAuthorName = authorName;
        mDateOfPublication = date;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getDateOfPublication() {
        return mDateOfPublication;
    }
}
