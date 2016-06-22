package com.example.bogdan.dou_feed.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class ArticleEntity {
    private List<Element> mArticleElements;

    public ArticleEntity() {
        mArticleElements = new ArrayList<>();
    }

    public int size() {
        return mArticleElements.size();
    }

    public void addElement(String tag, String content) {
        Element element;
        switch (tag) {
            case "p":
                element = new Element(Type.CONTENT, content);
                mArticleElements.add(element);
                break;
            case "img":
                element = new Element(Type.IMAGE, content);
                mArticleElements.add(element);
                break;
//            case "":
//                element = new Element(Type.CONTENT_HEADING, content);
//                mArticleElements.add(element);
//                break;
            case "pre":
                element = new Element(Type.CONTENT_CODE, content);
                mArticleElements.add(element);
                break;
            default:
                break;
        }
    }

    public Element getElement(int position) {
        return mArticleElements.get(position);
    }

    private class Element {
        private Type mType;
        private String mContent;

        Element(Type type, String content) {
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

    enum Type {
        CONTENT,
        IMAGE,
        CONTENT_HEADING,
        CONTENT_CODE
    }


}
