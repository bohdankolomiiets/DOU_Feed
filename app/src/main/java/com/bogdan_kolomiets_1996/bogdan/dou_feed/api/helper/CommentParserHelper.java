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

  private static final String ID_COMMENTS_BLOCK = "commentsList";

  private static final String SELECTOR_IMAGE_URL = "img.g-avatar";
  private static final String SELECTOR_AUTHOR_NAME = "a";
  private static final String SELECTOR_DATE = ".comment-link";
  private static final String SELECTOR_CONTENT = ".text.b-typo";

  private static final String ATTR_SELECTOR_IMG = "src";

  private List<CommentItem> mComments;

  public CommentParserHelper() {
    mComments = new ArrayList<>();
  }

  public List<CommentItem> parseComments(Document document) {
    Element commentBlock = selectCommentsBlock(document);
    for (Element item : commentBlock.children()) {
      addCommentToList(item);
    }

    return mComments;
  }

  private Element selectCommentsBlock(Document document) {
    return document.getElementById(ID_COMMENTS_BLOCK);
  }

  private void addCommentToList(Element item) {
    CommentItem comment = createComment(item);
    mComments.add(comment);
  }

  private CommentItem createComment(Element item) {
    Header header = createHeader(item);
    String content = getContent(item);

    return new CommentItem(header, content);
  }

  private Header createHeader(Element item) {
    String imageUrl = getImageUrl(item);
    String authorName = getAuthorName(item);
    String date = getTxtDate(item);

    return new Header(imageUrl, authorName, date);
  }

  private String getImageUrl(Element commentItem) {
    return commentItem.select(SELECTOR_IMAGE_URL).first().attr(ATTR_SELECTOR_IMG);
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
    return commentItem.select(SELECTOR_AUTHOR_NAME).first().text();
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
    return commentItem.select(SELECTOR_DATE).first().text();
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
    return item.select(SELECTOR_CONTENT).first().text();
  }
}
