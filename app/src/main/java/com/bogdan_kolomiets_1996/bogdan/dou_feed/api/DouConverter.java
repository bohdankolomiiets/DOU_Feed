package com.bogdan_kolomiets_1996.bogdan.dou_feed.api;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.article.Article;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.CommentItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouConverter implements Converter<ResponseBody, Object> {
  private static DouParser mParser = null;
  private Type mType;

  public DouConverter(Type type) {
    mType = type;
    if (mParser == null) {
      mParser = new DouParser();
    }
  }

  @Override
  public Object convert(ResponseBody value) throws IOException {
    if (mType.toString().equals(new TypeToken<List<FeedItem>>() {}.getType().toString())) {
      return mParser.parseFeed(Jsoup.parse(value.string()));
    } else if (mType.toString().equals(new TypeToken<Article>() {}.getType().toString())) {
      return mParser.parseArticle(Jsoup.parse(value.string()));
    } else if (mType.toString().equals(new TypeToken<List<CommentItem>>() {}.getType().toString())) {
      return mParser.parseComments(Jsoup.parse(value.string()));
    }
    throw new IllegalArgumentException("This type does not supported " + mType);
  }


}
