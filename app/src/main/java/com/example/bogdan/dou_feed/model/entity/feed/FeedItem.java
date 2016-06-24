package com.example.bogdan.dou_feed.model.entity.feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class FeedItem {
    private final String mUrl;
    private final FeedItemHeader mHeader;
    private final FeedItemFooter mFooter;
    private final FeedItemContent mContent;

    private FeedItem(Builder builder) {
        mUrl = builder.mUrl;
        mHeader = builder.mHeader;
        mFooter = builder.mFooter;
        mContent = builder.mContent;
    }

    public String getUrl() {
        return mUrl;
    }

    public FeedItemHeader getHeader() {
        return mHeader;
    }

    public FeedItemFooter getFooter() {
        return mFooter;
    }

    public FeedItemContent getContent() {
        return mContent;
    }

    public static class Builder {
        private String mUrl;
        private FeedItemHeader mHeader;
        private FeedItemContent mContent;
        private FeedItemFooter mFooter;

        public Builder() {

        }

        public Builder url(String url) {
            mUrl = url;
            return this;
        }

        public Builder header(FeedItemHeader header) {
            mHeader = header;
            return this;
        }

        public Builder content(FeedItemContent content) {
            mContent = content;
            return this;
        }

        public Builder footer(FeedItemFooter footer) {
            mFooter = footer;
            return this;
        }

        public FeedItem build() {
            return new FeedItem(this);
        }
    }

}
