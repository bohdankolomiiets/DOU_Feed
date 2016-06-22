package com.example.bogdan.dou_feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bogdan.dou_feed.model.entity.CommentItemEntity;
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
    private List<CommentItemEntity> mCommentList;

    public CommentsAdapter(Context context) {
        mContext = context;
        mCommentList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.comment_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CommentItemEntity comment = mCommentList.get(position);

        Picasso.with(mContext)
                .load(comment.getImageUrl())
                .into(holder.avatar);
        holder.author.setText(comment.getAuthorName());
        holder.date.setText(comment.getDateOfPublication());
        holder.text.setText(comment.getText());
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public void addComments(List<CommentItemEntity> list) {
        mCommentList.addAll(list);
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.commentAvatar)
        CircleImageView avatar;

        @BindView(R.id.commenetAuthor)
        TextView author;

        @BindView(R.id.commentDate)
        TextView date;

        @BindView(R.id.commentText)
        TextView text;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
