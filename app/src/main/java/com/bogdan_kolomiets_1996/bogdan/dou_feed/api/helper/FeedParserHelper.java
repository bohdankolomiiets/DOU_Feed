package com.bogdan_kolomiets_1996.bogdan.dou_feed.api.helper;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.Content;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.Footer;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.Header;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.08.16
 */
public class FeedParserHelper {
  private static final int EMPTY_COUNT = 0;
  private static final String EMPTY_COMMENT_URL = null;

  private List<FeedItem> mFeed;

  public FeedParserHelper() {
    mFeed = new ArrayList<>();
  }

  public List<FeedItem> parseFeed(Document document) {
    Elements feedItems = selectFeedItems(document);

    for (Element item : feedItems) {
      String url = getUrl(item);

      Header header = createHeader(item);
      Content content = createContent(item);
      Footer footer = createFooter(item);

      FeedItem feedItemEntity = new FeedItem.Builder()
          .url(url)
          .header(header)
          .content(content)
          .footer(footer)
          .build();

      mFeed.add(feedItemEntity);
    }

    return mFeed;
  }

  private Elements selectFeedItems(Document document) {
    return document.select(".b-lenta article");
  }

  private String getUrl(Element item) {
    return item.select("h2 a").first().attr("href");
  }

  private Header createHeader(Element item) {
    String imageUrl = item.select("h2 a img").first().attr("src");
    String authorName = item.select(".author").first().html();
    String date = item.select(".date").first().text();

    return new Header(imageUrl, authorName, date);
  }

  private Content createContent(Element item) {
    String title = item.select("h2 a").first().text().replace("&nbsp;", " ");
    String description = item.select(".b-typo").first().text();
    description = deleteCommentCount(description);

    return new Content(title, description);
  }

  private Footer createFooter(Element item) {
    int watchCount = getWatchCount(item);
    int commentCount = getCommentCount(item);
    String commentUrl = getCommentUrl(item);

    return new Footer(watchCount, commentCount, commentUrl);
  }

  private int getWatchCount(Element item) {
    int watchCount;
    try {
      watchCount = selectWatchCount(item);
    } catch (NullPointerException e) {
      watchCount = EMPTY_COUNT;
    }

    return watchCount;
  }

  private int selectWatchCount(Element item) {
    return Integer.parseInt(item.select(".pageviews").first().text());
  }

  private int getCommentCount(Element item) {
    int commentCount;
    try {
      commentCount = selectCommentCount(item);
    } catch (NullPointerException e) {
      commentCount = EMPTY_COUNT;
    }

    return commentCount;
  }

  private int selectCommentCount(Element item) {
    return Integer.parseInt(item.select(".b-typo a").first().html());
  }

  private String getCommentUrl(Element item) {
    String commentUrl;
    try {
      commentUrl = selectCommentUrl(item);
    } catch (NullPointerException e) {
      commentUrl = EMPTY_COMMENT_URL;
    }
    return commentUrl;
  }

  private String selectCommentUrl(Element item) {
    return item.select(".b-typo a").first().attr("href");
  }

  private String deleteCommentCount(String text) {
    char[] charText = text.toCharArray();
    int end = charText.length - 1;

    for (int i = end; i >= 0; i--) {
      try {
        Integer.parseInt(String.valueOf(charText[i]));
        end--;
      } catch (NumberFormatException e) {
        break;
      }
    }

    return String.valueOf(charText).substring(0, end + 1);
  }

}
