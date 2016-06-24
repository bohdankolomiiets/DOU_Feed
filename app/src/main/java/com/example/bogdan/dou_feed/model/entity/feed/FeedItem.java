package com.example.bogdan.dou_feed.model.entity.feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class FeedItem {
    private final String mUrl;
    private final Header mHeader;
    private final Footer mFooter;
    private final Content mContent;

    private FeedItem(Builder builder) {
        mUrl = builder.mUrl;
        mHeader = builder.mHeader;
        mFooter = builder.mFooter;
        mContent = builder.mContent;
    }

    public String getUrl() {
        return mUrl;
    }

    public Header getHeader() {
        return mHeader;
    }

    public Footer getFooter() {
        return mFooter;
    }

    public Content getContent() {
        return mContent;
    }

    public static class Builder {
        private String mUrl;
        private Header mHeader;
        private Content mContent;
        private Footer mFooter;

        public Builder() {

        }

        public Builder url(String url) {
            mUrl = url;
            return this;
        }

        public Builder header(Header header) {
            mHeader = header;
            return this;
        }

        public Builder content(Content content) {
            mContent = content;
            return this;
        }

        public Builder footer(Footer footer) {
            mFooter = footer;
            return this;
        }

        public FeedItem build() {
            return new FeedItem(this);
        }
    }

}
