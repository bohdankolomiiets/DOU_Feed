package com.example.bogdan.dou_feed.di;

import com.example.bogdan.dou_feed.view.CommentsFragment;

import dagger.Component;
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
