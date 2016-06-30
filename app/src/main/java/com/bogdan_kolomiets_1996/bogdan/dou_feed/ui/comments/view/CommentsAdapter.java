package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.comments.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.CommentItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.Holder> {
    private Context mContext;
    private List<CommentItem> mCommentsList;

    public CommentsAdapter(Context context) {
        mContext = context;
        mCommentsList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.comment_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CommentItem comment = mCommentsList.get(position);

        Picasso.with(mContext)
                .load(comment.getHeader().getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.avatarView);
        holder.authorView.setText(comment.getHeader().getAuthorName());
        holder.dateView.setText(comment.getHeader().getDate());
        holder.contentView.setText(comment.getText());
    }

    @Override
    public int getItemCount() {
        return mCommentsList.size();
    }

    public void clear() {
        mCommentsList.clear();
        notifyDataSetChanged();
    }

    public void addComments(List<CommentItem> comments) {
        mCommentsList.addAll(comments);
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.commentAvatar)
        CircleImageView avatarView;

        @BindView(R.id.commenetAuthor)
        TextView authorView;

        @BindView(R.id.commentDate)
        TextView dateView;

        @BindView(R.id.commentText)
        TextView contentView;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
