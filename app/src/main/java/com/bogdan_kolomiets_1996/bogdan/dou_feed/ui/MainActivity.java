package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;

import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class MainActivity extends AppCompatActivity {
  private Toolbar mToolbar;

  private FragmentLauncher mLauncher; // TODO: 01.08.16 add it to DI

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
}
