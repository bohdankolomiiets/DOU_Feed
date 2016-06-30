package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public abstract class EndlessOnScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private boolean loading = true;

    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLayoutManager;

    public EndlessOnScrollListener(LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = mLayoutManager.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            onLoadMore();
            loading = true;
        }
    }

    public abstract void onLoadMore();
}
