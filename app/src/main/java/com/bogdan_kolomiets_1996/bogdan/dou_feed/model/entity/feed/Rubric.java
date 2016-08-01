package com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 25.07.16
 */
public class Rubric {
  private String mUrl;
  private String mName;

  public Rubric(String url, String name) {
    mUrl = url;
    mName = name;
  }

  public String getUrl() {
    return mUrl;
  }

  public String getName() {
    return mName;
  }
}
