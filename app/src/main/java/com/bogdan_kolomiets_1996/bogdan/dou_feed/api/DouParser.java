package com.bogdan_kolomiets_1996.bogdan.dou_feed.api;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.CommentItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.article.Article;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.article.ArticleHeader;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.Header;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouParser {

  public static List<FeedItem> parseFeed(Document document) {
    FeedParserHelper helper = new FeedParserHelper();
    return helper.parseFeed(document);
  }


  public static Article parseArticle(Document document) {
    Article articlePage = new Article(createArticleHeader(document));

    Elements elements = selectArticlePageElements(document);
    for (Element pageElement : elements) {
      switch (pageElement.tagName()) {
        case "p":
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
        case "h1":
        case "h2":
        case "h3":
        case "h4":
        case "h5":
        case "h6":
          articlePage.addElement(new Heading(pageElement.text()));
          break;
        case "pre":
          articlePage.addElement(new Code(pageElement.text()));
          break;
        case "blockquote":
          articlePage.addElement(new Blockquote(pageElement.text()));
          break;
        case "table":
          articlePage.addElement(createTable(pageElement));
          break;
        case "ul":
          articlePage.addElement(createListElement(pageElement));
          break;
        case "ol":
          articlePage.addElement(createListElement(pageElement));
          break;
      }
      if (pageElement.children().hasAttr("src") && !pageElement.children().hasAttr("scrolling")) {
        articlePage.addElement(new Image(pageElement.children().attr("src")));
      }
    }

    return articlePage;
  }

  private static ArticleHeader createArticleHeader(Document document) {
    String authorName = document.select(".b-post-info .author .name a").first().html();
    String date = document.select(".b-post-info .date").first().text();
    String title = document.select("article.b-typo h1").first().text().replace("&nbsp;", " ");
    ArticleHeader header = new ArticleHeader(authorName, date, title);

    return header;
  }

  private static Elements selectArticlePageElements(Document document) {
    return document.select("article.b-typo div").first().children();
  }

  private static Table createTable(Element tableFromPage) {
    Table table = new Table();
    for (Element tableElements : tableFromPage.children()) {
      switch (tableElements.tagName()) {
        case "thead":
          table.addTableRow();
          for (Element head : tableElements.children().first().children()) {
            table.addRowCell(head.text());
          }
          break;
        case "tbody":
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

  private static ListElement createListElement(Element listFromPage) {
    ListElement list = new ListElement();
    for (Element listChildren : listFromPage.children()) {
      if (listChildren.tagName().equals("li")) {
        list.add(listChildren.text());
      }
    }

    return list;
  }

  public static List<CommentItem> parseComments(Document document) {
    List<CommentItem> commentsList = new ArrayList<>();

    Element commentBlock = document.getElementById("commentsList");
    for (Element commentItem : commentBlock.children()) {
      String imageUrl = commentItem.select("img.g-avatar").first().attr("src");
      String authorName;
      String date;
      try {
        authorName = commentItem.select("a").first().text();
      } catch (NullPointerException e) {
        authorName = "Unknown";
      }
      try {
        date = commentItem.select(".comment-link").first().text();
      } catch (NullPointerException e) {
        date = "";
      }
      Header header = new Header(imageUrl, authorName, date);

      String content;
      try {
        content = commentItem.select(".text.b-typo").first().text();
      } catch (NullPointerException e) {
        content = "";
      }

      CommentItem comment = new CommentItem(header, content);
      commentsList.add(comment);
    }

    return commentsList;
  }
}
