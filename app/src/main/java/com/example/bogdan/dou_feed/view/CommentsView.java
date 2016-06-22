package com.example.bogdan.dou_feed.view;

import com.example.bogdan.dou_feed.model.entity.CommentItemEntity;

import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public interface CommentsView extends View {

    void showComments(List<CommentItemEntity> comments);

    void stopRefresh();
}
