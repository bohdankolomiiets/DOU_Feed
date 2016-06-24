package com.example.bogdan.dou_feed.model.entity.feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 24.06.16
 */
public class FeedItemFooter {
    private int mWatchCount;
    private int mCommentCount;
    private String mCommentUrl;

    public FeedItemFooter(int watchCount, int commentCount, String commentUrl) {
        mWatchCount = watchCount;
        mCommentCount = commentCount;
        mCommentUrl = commentUrl;
    }

    public int getWatchCount() {
        return mWatchCount;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public String getCommentUrl() {
        return mCommentUrl;
    }
}
