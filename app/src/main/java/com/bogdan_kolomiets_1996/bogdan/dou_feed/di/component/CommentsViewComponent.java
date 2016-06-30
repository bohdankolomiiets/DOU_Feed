package com.bogdan_kolomiets_1996.bogdan.dou_feed.di.component;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.ActivityScope;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.module.CommentViewModule;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.comments.view.CommentsFragment;

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
