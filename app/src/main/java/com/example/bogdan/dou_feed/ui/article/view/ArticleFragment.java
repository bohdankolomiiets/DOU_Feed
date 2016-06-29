package com.example.bogdan.dou_feed.ui.article.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.di.module.ArticleViewModule;
import com.example.bogdan.dou_feed.model.entity.page.PageElement;
import com.example.bogdan.dou_feed.ui.article.presenter.ArticlePresenter;
import com.example.bogdan.dou_feed.ui.common.BaseFragment;
import com.example.bogdan.dou_feed.ui.comments.view.CommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class ArticleFragment extends BaseFragment implements ArticleView {
    private static final int LAYOUT = R.layout.article_layout;
    private String mRubric;
    private String mUrl;

    @BindView(R.id.articleContainer)
    LinearLayout container;

    @BindView(R.id.articleAuthor)
    TextView authorView;

    @BindView(R.id.articleDate)
    TextView dateView;

    @BindView(R.id.articleTitle)
    TextView titleView;

    @Inject
    ArticlePresenter presenter;

    private LayoutInflater mLayoutInflater;

    public static ArticleFragment newInstance(String rubric, String url) {
        ArticleFragment fragment = new ArticleFragment();

        Bundle args = new Bundle();
        args.putString(RUBRIC_KEY, rubric);
        args.putString(URL_KEY, url);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DouApp.getAppComponent().plus(new ArticleViewModule(this)).inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mRubric = getArguments().getString(RUBRIC_KEY);
        mUrl = getArguments().getString(URL_KEY);
        presenter.onCreate(mRubric, mUrl);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);
        mLayoutInflater = inflater;
        presenter.onCreateView(savedInstanceState);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.article_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.goComments:
                CommentsFragment fragment = CommentsFragment.newInstance(mRubric, mUrl);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment, null)
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    @Override
    public void showPageElement(PageElement element) {
        element.display(mLayoutInflater, container);
    }

    @Override
    public void showHead(String author, String date, String title) {
        authorView.setText(author);
        dateView.setText(date);
        titleView.setText(title);
    }
}
