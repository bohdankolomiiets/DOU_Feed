package com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 24.06.16
 */
public class Footer {
    private int mWatchCount;
    private int mCommentCount;
    private String mCommentUrl;

    public Footer(int watchCount, int commentCount, String commentUrl) {
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
