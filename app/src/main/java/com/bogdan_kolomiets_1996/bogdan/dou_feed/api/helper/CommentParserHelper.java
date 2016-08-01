package com.bogdan_kolomiets_1996.bogdan.dou_feed.api.helper;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.CommentItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.Header;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.08.16
 */
public class CommentParserHelper {
  private static final String EMPTY_AUTHOR_NAME = "Unknown";
  private static final String EMPTY_DATE = "";
  private static final String EMPTY_CONTENT = "";

  private List<CommentItem> mComments;

  @Inject
  public CommentParserHelper() {
    mComments = new ArrayList<>();
  }

  public List<CommentItem> parseComments(Document document) {
    Element commentBlock = selectCommentsBlock(document);
    for (Element commentItem : commentBlock.children()) {
      String imageUrl = selectImageUrl(commentItem);
      String authorName = getAuthorName(commentItem);
      String date = getTxtDate(commentItem);

      Header header = new Header(imageUrl, authorName, date);

      String content = getContent(commentItem);
      CommentItem comment = new CommentItem(header, content);
      mComments.add(comment);
    }

    return mComments;
  }

  private Element selectCommentsBlock(Document document) {
    return document.getElementById("commentsList");
  }

  private String selectImageUrl(Element commentItem) {
    return commentItem.select("img.g-avatar").first().attr("src");
  }

  private String getAuthorName(Element commentItem) {
    String authorName;
    try {
      authorName = selectAuthorName(commentItem);
    } catch (NullPointerException e) {
      authorName = EMPTY_AUTHOR_NAME;
    }

    return authorName;
  }

  private String selectAuthorName(Element commentItem) {
    return commentItem.select("a").first().text();
  }

  private String getTxtDate(Element commentItem) {
    String date;
    try {
      date = selectDate(commentItem);
    } catch (NullPointerException e) {
      date = EMPTY_DATE;
    }

    return date;
  }

  private String selectDate(Element commentItem) {
    return commentItem.select(".comment-link").first().text();
  }

  private String getContent(Element item) {
    String content;
    try {
      content = selectContent(item);
    } catch (NullPointerException e) {
      content = EMPTY_CONTENT;
    }

    return content;
  }

  private String selectContent(Element item) {
    return item.select(".text.b-typo").first().text();
  }
}
