package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.comments.view;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.CommentItem;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.common.View;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public interface CommentsView extends View {

    void showComments(List<CommentItem> comments);

    void stopRefresh();

    void showEmptyList();
}
