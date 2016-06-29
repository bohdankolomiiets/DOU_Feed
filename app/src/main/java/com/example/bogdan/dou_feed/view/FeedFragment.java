package com.example.bogdan.dou_feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.EndlessOnScrollListener;
import com.example.bogdan.dou_feed.FeedAdapter;
import com.example.bogdan.dou_feed.HTTPUtils;
import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.di.module.FeedViewModule;
import com.example.bogdan.dou_feed.model.entity.Category;
import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.example.bogdan.dou_feed.presenter.FeedPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class FeedFragment extends BaseFragment implements FeedView, FeedAdapter.OnFeedItemClickListener {

  @BindView(R.id.feedRecyclerView)
  RecyclerView feedRecyclerView;

  @Inject
  FeedPresenter presenter;

  @BindView(R.id.swipeContainer)
  SwipeRefreshLayout swipeLayout;

  private LinearLayoutManager mLayoutManager;
  private FeedAdapter mFeedAdapter;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    DouApp.getAppComponent().plus(new FeedViewModule(this)).inject(this);
    View view = inflater.inflate(R.layout.feed_layout, container, false);
    ButterKnife.bind(this, view);
    mLayoutManager = new LinearLayoutManager(getContext());
    feedRecyclerView.setLayoutManager(mLayoutManager);

    swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mFeedAdapter.clear();
        presenter.onRefresh();
      }
    });

    feedRecyclerView.addOnScrollListener(new EndlessOnScrollListener(mLayoutManager) {
      @Override
      public void onLoadMore() {
        presenter.loadFeed(false);
      }
    });

    mFeedAdapter = new FeedAdapter(getContext(), this);
    feedRecyclerView.setAdapter(mFeedAdapter);

    presenter.onCreateView();
    return view;
  }

  @Override
  public void showFeed(List<FeedItem> feed) {
    mFeedAdapter.addFeed(feed);
  }

  @Override
  public void stopRefresh() {
    swipeLayout.setRefreshing(false);
  }

  @Override
  public void onClick(String url, FeedAdapter.Type type) {
    System.out.println("URL = " + url);
    String rubric = HTTPUtils.getRubric(url);
    System.out.println("RUBRIC " + rubric);
    String urlPage = HTTPUtils.getPageUrl(url);
    switch (type) {
      case COMMENT:
        CommentsFragment cf = CommentsFragment.newInstance(rubric, urlPage);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, cf, null)
                .addToBackStack(null)
                .commit();
        break;
      case FEED_ITEM:
        System.out.println("Rubric = " + rubric + " Page = " + urlPage);
        ArticleFragment fragment = ArticleFragment.newInstance(rubric, urlPage);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, null)
                .addToBackStack(null)
                .commit();
        break;
    }
  }

  @Override
  public void showLoading() {
    showProgressDial();
  }

  @Override
  public void hideLoading() {
    hideProgressDial();
  }

  @Override
  public void showError(String message) {
    onError(message);
  }
}
