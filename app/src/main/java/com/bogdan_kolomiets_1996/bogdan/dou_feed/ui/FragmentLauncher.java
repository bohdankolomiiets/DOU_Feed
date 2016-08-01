package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.HTTPUtils;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.view.ArticleFragment;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.view.FeedFragment;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.07.16
 */
public class FragmentLauncher implements FragmentManager.OnBackStackChangedListener{
  private static final String ACTION_MAIN = "android.intent.action.MAIN";
  private static final String FEED_PATH = "/lenta/";

  private static FragmentLauncher mInstance = null;
  private AppCompatActivity mActivity;
  private int mContainer;
  private Intent mIntent;

  private FragmentLauncher(AppCompatActivity activity) {
    mActivity = activity;
    mIntent = activity.getIntent();
  }

  public static FragmentLauncher with(AppCompatActivity activity) {
    if (mInstance == null) {
      mInstance = new FragmentLauncher(activity);
    }

    return mInstance;
  }

  public FragmentLauncher into(int container) {
    mContainer = container;

    return mInstance;
  }

  public void open() {
    if (isActionMain()) {
      startFeedFragment();
    } else {
      startFragmentFromAction();
    }
  }

  @Override
  public void onBackStackChanged() {
    isDisplayingHomeBtn();
  }

  public void onNewIntent(Intent intent) {
    mIntent = intent;
    startFragmentFromAction();
  }

  private void startFeedFragment() {
    startFragment(new FeedFragment());
  }

  private void startFragmentFromAction() {
    Uri uri = mIntent.getData();
    String path = uri.getPath();
    switch (path) {
      case FEED_PATH:
        startFeedFragment();
        break;
      default:
        startArticleFragment(path);
        break;
    }
  }

  private void startArticleFragment(String path) {
    String rubric = HTTPUtils.getRubricFromPath(path);
    String url = HTTPUtils.getPageUrlFromPath(path);

    startFragment(ArticleFragment.newInstance(rubric, url));
  }

  private boolean isActionMain() {
    String action = mIntent.getAction();

    return action.equalsIgnoreCase(ACTION_MAIN);
  }

  private void startFragment(Fragment fragment) {
    mActivity.getSupportFragmentManager().addOnBackStackChangedListener(this);
    mActivity.getSupportFragmentManager()
    .beginTransaction()
    .replace(mContainer, fragment)
    .commit();
  }

  private boolean isEmptyBackStack() {
    return mActivity.getSupportFragmentManager().getBackStackEntryCount() > 0;
  }

  private void isDisplayingHomeBtn() {
    boolean display = isEmptyBackStack();
    displayHomeBtn(display);
  }

  private void displayHomeBtn(boolean display) {
    mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(display);
    mActivity.getSupportActionBar().setDisplayShowHomeEnabled(display);
  }
}
