package com.example.bogdan.dou_feed.model.entity;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class FeedItemEntity {
    private String mUrl;
    private String mAuthor;
    private String mDate;
    private int mWatchCount;
    private int mCommentCount;
    private String mTitle;
    private String mDescription;
    private String mTopic;
    private String mTags;

    public FeedItemEntity() {
    }

    public FeedItemEntity(String url, String author, String date, int watchCount, int commentCount, String title, String description, String topic, String tags) {
        mUrl = url;
        mAuthor = author;
        mDate = date;
        mWatchCount = watchCount;
        mCommentCount = commentCount;
        mTitle = title;
        mDescription = description;
        mTopic = topic;
        mTags = tags;
    }

    @Override
    public String toString() {
        return "FeedItem = ["
                + "url: " + mUrl
                + ", author: " + mAuthor
                + ", date: " + mDate
                + ", watchCount: " + mWatchCount
                + ", title: " + mTitle
                + ", description: " + mDescription
                + ", topic: " + mTopic
                + ", tags: " + mTags;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public void setCommentCount(int commentCount) {
        mCommentCount = commentCount;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getmWatchCount() {
        return mWatchCount;
    }

    public void setmWatchCount(int mWatchCount) {
        this.mWatchCount = mWatchCount;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmTopic() {
        return mTopic;
    }

    public void setmTopic(String mTopic) {
        this.mTopic = mTopic;
    }

    public String getmTags() {
        return mTags;
    }

    public void setmTags(String mTags) {
        this.mTags = mTags;
    }
}
