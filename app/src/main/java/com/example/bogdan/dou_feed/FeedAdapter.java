package com.example.bogdan.dou_feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bogdan.dou_feed.model.entity.FeedItemEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.Holder> {
    private Context mContext;
    private List<FeedItemEntity> mFeedList;
    private OnFeedItemClickListener mListener;

    public FeedAdapter(Context context, OnFeedItemClickListener listener) {
        mContext = context;
        mFeedList = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.feed_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        FeedItemEntity feedItem = mFeedList.get(position);

        Picasso.with(mContext)
                .load(feedItem.getImageUrl())
                .into(holder.articleImage);
        holder.author.setText(feedItem.getmAuthor());
        holder.date.setText(feedItem.getmDate());
        holder.title.setText(feedItem.getmTitle());
        if (feedItem.getmWatchCount() != 0)
            holder.watchCount.setText(String.valueOf(feedItem.getmWatchCount()));
        if (feedItem.getCommentCount() != 0)
            holder.commentCount.setText(String.valueOf(feedItem.getCommentCount()));
        holder.description.setText(feedItem.getmDescription());

    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public void clear() {
        mFeedList.clear();
        notifyDataSetChanged();
    }

    public void addFeed(List<FeedItemEntity> feedList) {
        mFeedList.addAll(feedList);
        notifyDataSetChanged();
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.commentContainer)
        LinearLayout commentContainer;

        @BindView(R.id.articleImage)
        CircleImageView articleImage;

        @BindView(R.id.author)
        TextView author;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.watchCount)
        TextView watchCount;

        @BindView(R.id.commentCount)
        TextView commentCount;

        @BindView(R.id.description)
        TextView description;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            commentContainer.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            String url = null;
            switch (v.getId()) {
                case R.id.commentContainer:
                    url = mFeedList.get(getLayoutPosition()).getCommentUrl();
                    break;
                default:
                    url = mFeedList.get(getLayoutPosition()).getmUrl();
            }
            if (url != null) {
                mListener.onClick(url);
            }
        }
    }

    public interface OnFeedItemClickListener {
        void onClick(String url);
    }
}
