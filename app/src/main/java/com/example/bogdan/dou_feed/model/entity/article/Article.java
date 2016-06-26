package com.example.bogdan.dou_feed.model.entity.article;

import com.example.bogdan.dou_feed.model.entity.Element;
import com.example.bogdan.dou_feed.model.entity.Link;
import com.example.bogdan.dou_feed.model.entity.TableEntity;
import com.example.bogdan.dou_feed.model.entity.article.ArticleHeader;
import com.example.bogdan.dou_feed.model.entity.page.PageElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class Article {
    private ArticleHeader mHeader;

    private List<PageElement> mArticleElements;

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

    public void addElement(PageElement element) {
        mArticleElements.add(element);
    }
//
//    public void addTable(TableEntity table) {
//        mArticleElements.add(table);
//    }
}
