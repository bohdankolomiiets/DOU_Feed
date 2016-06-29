package com.example.bogdan.dou_feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;
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
  private List<FeedItem> mFeedList;
  private OnFeedItemClickListener mOnClickListener;

  public FeedAdapter(Context context, OnFeedItemClickListener listener) {
    mContext = context;
    mFeedList = new ArrayList<>();
    mOnClickListener = listener;
  }

  @Override
  public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new Holder(((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
            .inflate(R.layout.feed_row, parent, false));
  }

  @Override
  public void onBindViewHolder(Holder holder, int position) {
    FeedItem feedItem = mFeedList.get(position);

    Picasso.with(mContext)
            .load(feedItem.getHeader().getImageUrl())
            .into(holder.articleImageView);
    holder.authorView.setText(feedItem.getHeader().getAuthorName());
    holder.dateView.setText(feedItem.getHeader().getDate());
    holder.titleView.setText(feedItem.getContent().getTitle());
    holder.descriptionView.setText(feedItem.getContent().getDescription());
    if (feedItem.getFooter().getWatchCount() != 0)
      holder.watchCountView.setText(String.valueOf(feedItem.getFooter().getWatchCount()));
    if (feedItem.getFooter().getCommentCount() != 0)
      holder.commentCountView.setText(String.valueOf(feedItem.getFooter().getCommentCount()));


  }

  @Override
  public int getItemCount() {
    return mFeedList.size();
  }

  public void clear() {
    mFeedList.clear();
    notifyDataSetChanged();
  }

  public void addFeed(List<FeedItem> feed) {
    mFeedList.addAll(feed);
    notifyDataSetChanged();
  }


  class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.commentContainer)
    LinearLayout commentContainer;

    @BindView(R.id.articleImage)
    CircleImageView articleImageView;

    @BindView(R.id.author)
    TextView authorView;

    @BindView(R.id.date)
    TextView dateView;

    @BindView(R.id.title)
    TextView titleView;

    @BindView(R.id.watchCount)
    TextView watchCountView;

    @BindView(R.id.commentCount)
    TextView commentCountView;

    @BindView(R.id.description)
    TextView descriptionView;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      commentContainer.setOnClickListener(this);
      itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
      String url;
      Type type;
      switch (v.getId()) {
        case R.id.commentContainer:
          type = Type.COMMENT;
          url = mFeedList.get(getLayoutPosition()).getFooter().getCommentUrl();
          break;
        default:
          type = Type.FEED_ITEM;
          url = mFeedList.get(getLayoutPosition()).getUrl();
      }
      if (url != null) {
        mOnClickListener.onClick(url, type);
      }
    }
  }

  public interface OnFeedItemClickListener {
    void onClick(String url, Type type);
  }

  public enum Type {
    COMMENT,
    FEED_ITEM
  }
}
