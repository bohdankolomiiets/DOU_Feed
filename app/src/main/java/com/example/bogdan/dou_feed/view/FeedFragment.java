package com.example.bogdan.dou_feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.EndlessOnScrollListener;
import com.example.bogdan.dou_feed.FeedAdapter;
import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.di.FeedViewModule;
import com.example.bogdan.dou_feed.model.entity.FeedItemEntity;
import com.example.bogdan.dou_feed.presenter.FeedPresenter;
import com.example.bogdan.dou_feed.presenter.FeedPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class FeedFragment extends BaseFragment implements FeedView {
    @BindView(R.id.feedRecyclerView)
    RecyclerView feedRecyclerView;

    @Inject
    FeedPresenter presenter;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

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

        feedRecyclerView.addOnScrollListener(new EndlessOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore() {
                presenter.loadFeed();
            }
        });

        mFeedAdapter = new FeedAdapter(getContext());
        feedRecyclerView.setAdapter(mFeedAdapter);


        presenter.onCreateView();
        return view;
    }



    @Override
    public void showFeed(List<FeedItemEntity> feed) {
        mFeedAdapter.addFeed(feed);
    }




}
