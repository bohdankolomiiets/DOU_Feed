package com.example.bogdan.dou_feed.model.entity.article;

import com.example.bogdan.dou_feed.model.entity.Element;
import com.example.bogdan.dou_feed.model.entity.TableEntity;
import com.example.bogdan.dou_feed.model.entity.article.ArticleHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class Article {
    private ArticleHeader mHeader;

    private List<Element> mArticleElements;

    public Article(ArticleHeader header) {
        mHeader = header;
        mArticleElements = new ArrayList<>();
    }

    public ArticleHeader getHeader() {
        return mHeader;
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
        TABLE,
        LIST_ELEMENT
    }


}
