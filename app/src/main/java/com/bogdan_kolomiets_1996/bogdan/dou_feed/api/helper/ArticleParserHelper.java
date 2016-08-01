package com.bogdan_kolomiets_1996.bogdan.dou_feed.api.helper;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.article.Article;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.article.ArticleHeader;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.Blockquote;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.Code;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.Heading;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.Image;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.ListElement;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.Table;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.Text;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.08.16
 */
public class ArticleParserHelper {
  private static final String PARAGRAPH_TAG = "p";
  private static final String HEADER1_TAG = "h1";
  private static final String HEADER2_TAG = "h2";
  private static final String HEADER3_TAG = "h3";
  private static final String HEADER4_TAG = "h4";
  private static final String HEADER5_TAG = "h5";
  private static final String HEADER6_TAG = "h6";
  private static final String CODE_TAG = "pre";
  private static final String BLOCKQUOTE_TAG = "blockquote";
  private static final String TABLE_TAG = "table";
  private static final String TABLE_HEAD_TAG = "thead";
  private static final String TABLE_BODY_TAG = "tbody";
  private static final String UL_LIST_TAG = "ul";
  private static final String OL_LIST_TAG = "ol";
  private static final String LIST_ITEM_TAG = "li";

  private static final String SELECTOR_AUTHOR_NAME = ".b-post-info .author .name a";
  private static final String SELECTOR_DATE = ".b-post-info .date";
  private static final String SELECTOR_TITLE = "article.b-typo h1";
  private static final String SELECTOR_PAGE_ELEMENTS = "article.b-typo div";

  public ArticleParserHelper() {

  }

  public Article parseArticle(Document document) {
    Article articlePage = new Article(createArticleHeader(document));

    Elements elements = selectArticlePageElements(document);
    for (Element pageElement : elements) {
      switch (pageElement.tagName()) {
        case PARAGRAPH_TAG:
          if (pageElement.hasText()) {
            for (Element children : pageElement.children()) {
              if (children.tagName().equals("src")) {
                articlePage.addElement(new Image(children.attr("src")));
              } else if (children.tagName().equals("a") &&
                  children.children().hasAttr("src")) {
                articlePage.addElement(new Image(children.children().attr("src")));
              }
            }
            articlePage.addElement(new Text(pageElement.text()));
          }
          break;
        case HEADER1_TAG:
        case HEADER2_TAG:
        case HEADER3_TAG:
        case HEADER4_TAG:
        case HEADER5_TAG:
        case HEADER6_TAG:
          articlePage.addElement(new Heading(pageElement.text()));
          break;
        case CODE_TAG:
          articlePage.addElement(new Code(pageElement.text()));
          break;
        case BLOCKQUOTE_TAG:
          articlePage.addElement(new Blockquote(pageElement.text()));
          break;
        case TABLE_TAG:
          articlePage.addElement(createTable(pageElement));
          break;
        case UL_LIST_TAG:
          articlePage.addElement(createListElement(pageElement));
          break;
        case OL_LIST_TAG:
          articlePage.addElement(createListElement(pageElement));
          break;
      }
      if (pageElement.children().hasAttr("src") && !pageElement.children().hasAttr("scrolling")) {
        articlePage.addElement(new Image(pageElement.children().attr("src")));
      }
    }

    return articlePage;
  }

  private ArticleHeader createArticleHeader(Document document) {
    String authorName = document.select(SELECTOR_AUTHOR_NAME).first().html();
    String date = document.select(SELECTOR_DATE).first().text();
    String title = document.select(SELECTOR_TITLE).first().text().replace("&nbsp;", " ");

    return new ArticleHeader(authorName, date, title);
  }

  private Elements selectArticlePageElements(Document document) {
    return document.select(SELECTOR_PAGE_ELEMENTS).first().children();
  }

  private Table createTable(Element tableFromPage) {
    Table table = new Table();
    for (Element tableElements : tableFromPage.children()) {
      switch (tableElements.tagName()) {
        case TABLE_HEAD_TAG:
          table.addTableRow();
          for (Element head : tableElements.children().first().children()) {
            table.addRowCell(head.text());
          }
          break;
        case TABLE_BODY_TAG:
          for (Element row : tableElements.children()) {
            table.addTableRow();
            for (Element column : row.children()) {
              table.addRowCell(column.text());
            }
          }
          break;
      }
    }

    return table;
  }

  private ListElement createListElement(Element listFromPage) {
    ListElement list = new ListElement();
    for (Element listChildren : listFromPage.children()) {
      if (listChildren.tagName().equals(LIST_ITEM_TAG)) {
        list.add(listChildren.text());
      }
    }

    return list;
  }

}
