package com.bogdan_kolomiets_1996.bogdan.dou_feed;

import android.app.Activity;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.FeedItem;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.07.16
 */
public class FacebookSharer {
  private static volatile FacebookSharer sInstance = null;
  private Activity mActivity;

  private FacebookSharer(Activity activity) {
    mActivity = activity;
  }

  public static FacebookSharer with(Activity activity) {
    if (sInstance == null) {
      sInstance = new FacebookSharer(activity);
    }

    return sInstance;
  }

  public void share(FeedItem item) {

  }

}
