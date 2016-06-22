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
        Element element = new Element(type, content);
        mArticleElements.add(element);
    }

    public Element getElement(int position) {
        return mArticleElements.get(position);
    }

    public Type getType(int position) {
        return mArticleElements.get(position).getType();
    }

    public String getContent(int position) {
        return mArticleElements.get(position).getContent();
    }


    private class Element {
        private Type mType;
        private String mContent;

        private Element(Type type, String content) {
            mType = type;
            mContent = content;
        }

        public Type getType() {
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
    }


}
