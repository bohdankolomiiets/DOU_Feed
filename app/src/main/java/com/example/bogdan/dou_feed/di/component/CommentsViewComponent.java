package com.example.bogdan.dou_feed.di.component;

import com.example.bogdan.dou_feed.di.ActivityScope;
import com.example.bogdan.dou_feed.di.module.CommentViewModule;
import com.example.bogdan.dou_feed.view.CommentsFragment;

import dagger.Subcomponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
@ActivityScope
@Subcomponent(modules = {CommentViewModule.class})
public interface CommentsViewComponent {
    void inject(CommentsFragment commentsFragment);
}
