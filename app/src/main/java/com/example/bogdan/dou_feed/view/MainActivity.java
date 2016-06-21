package com.example.bogdan.dou_feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.di.MainActivityModule;
import com.example.bogdan.dou_feed.model.entity.FeedItemEntity;
import com.example.bogdan.dou_feed.presenter.FeedPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class MainActivity extends AppCompatActivity implements FeedView {

    @Inject
    FeedPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DouApp.getAppComponent().plus(new MainActivityModule(MainActivity.this)).inject(this);
        presenter.onCreate();
    }

    @Override
    public void showFeed(List<FeedItemEntity> feed) {
        for (FeedItemEntity item : feed) {
            System.out.println(item.toString());
        }
    }
}
