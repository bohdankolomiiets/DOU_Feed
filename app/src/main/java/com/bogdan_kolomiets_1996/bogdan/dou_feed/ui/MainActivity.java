package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.FragmentLauncher;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.HTTPUtils;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page.Image;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.view.ArticleFragment;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.view.ImageDialog;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.view.FeedFragment;

import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class MainActivity extends AppCompatActivity implements Image.OnImageClickListener {
  private Toolbar mToolbar;

  private FragmentLauncher mLauncher;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_layout);
    ButterKnife.bind(this);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mToolbar);
    System.out.println("Application " + getApplication());
    System.out.println("Activity " + this);
    mLauncher = FragmentLauncher.with(this).into(R.id.container);
    mLauncher.open();
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    mLauncher.onNewIntent(intent);
  }

  @Override
  public boolean onSupportNavigateUp() {
    getSupportFragmentManager().popBackStack();
    return true;
  }

  @Override
  public void onImageClick(ImageView imageView) {
    Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    ImageDialog imageDialog = new ImageDialog(this);
    imageDialog.show();
    imageDialog.setImage(image);
  }
}
