package com.example.bogdan.dou_feed.di.component;

import com.example.bogdan.dou_feed.di.module.ApiModule;
import com.example.bogdan.dou_feed.di.module.AppModule;
import com.example.bogdan.dou_feed.di.module.CommentViewModule;
import com.example.bogdan.dou_feed.di.module.FeedViewModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    FeedViewComponent plus(FeedViewModule feedViewModule);

    CommentsViewComponent plus(CommentViewModule commentViewModule);
}
