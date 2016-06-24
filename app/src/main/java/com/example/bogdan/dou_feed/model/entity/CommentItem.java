package com.example.bogdan.dou_feed.model.entity;

import com.example.bogdan.dou_feed.model.entity.feed.Header;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class CommentItem {
    private Header mHeader;
    private String mText;

    public CommentItem(Header header, String text) {
        mHeader = header;
        mText = text;
    }

    public Header getHeader() {
        return mHeader;
    }

    public String getText() {
        return mText;
    }
}
