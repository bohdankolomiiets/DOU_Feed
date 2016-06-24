package com.example.bogdan.dou_feed.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class ArticleEntity {
    private String mAuthor;
    private String mDate;
    private String mTitle;

    private List<Element> mArticleElements;

    public ArticleEntity() {
        mArticleElements = new ArrayList<>();
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public int size() {
        return mArticleElements.size();
    }

    public void addElement(Type type, String content) {
        ArticleElement element = new ArticleElement(type, content);
        mArticleElements.add(element);
    }

    public void addTable(TableEntity table) {
        mArticleElements.add(table);
    }

    public Element getElement(int position) {
        return mArticleElements.get(position);
    }

    public Type getType(int position) {
        return getElement(position).getmType();
    }

    public String getContent(int position) {
        return ((ArticleElement)getElement(position)).getContent();
    }

    private class ArticleElement implements Element {
        private Type mType;
        private String mContent;

        private ArticleElement(Type type, String content) {
            mType = type;
            mContent = content;
        }

        public Type getmType() {
            return mType;
        }

        public String getContent() {
            return mContent;
        }
    }

    public enum Type {
        CONTENT,
        IMAGE,
        CONTENT_HEADING,
        CONTENT_CODE,
        LINK,
        BLOCKQUOTE,
        TABLE
    }


}
