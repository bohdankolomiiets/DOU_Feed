package com.bogdan_kolomiets_1996.bogdan.dou_feed.api;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.api.helper.ArticleParserHelper;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.api.helper.CommentParserHelper;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.api.helper.FeedParserHelper;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.CommentItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.article.Article;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouParser {
  private final FeedParserHelper mFeedHelper;
  private final CommentParserHelper mCommentHelper;
  private final ArticleParserHelper mArticleHelper;

  public DouParser() {
    mFeedHelper = new FeedParserHelper();
    mCommentHelper = new CommentParserHelper();
    mArticleHelper = new ArticleParserHelper();
  }

  public List<FeedItem> parseFeed(Document document) {
    return mFeedHelper.parseFeed(document);
  }


  public Article parseArticle(Document document) {
    return mArticleHelper.parseArticle(document);
  }

  public List<CommentItem> parseComments(Document document) {
    return mCommentHelper.parseComments(document);
  }
}
