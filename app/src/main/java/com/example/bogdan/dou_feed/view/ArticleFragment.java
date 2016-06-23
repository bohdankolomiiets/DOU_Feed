package com.example.bogdan.dou_feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.di.module.ArticleViewModule;
import com.example.bogdan.dou_feed.presenter.ArticlePresenter;
import com.squareup.picasso.Picasso;

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
        String rubric = getArguments().getString(RUBRIC_KEY);
        String url = getArguments().getString(URL_KEY);
        presenter.onCreate(rubric, url);
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
    public void showContent(String content) {
        TextView contentView = (TextView) mLayoutInflater.inflate(R.layout.article_content, null);
        contentView.setText(content);
        container.addView(contentView);
    }

    @Override
    public void showImage(String imageUrl) {
        ImageView imageView = (ImageView) mLayoutInflater.inflate(R.layout.article_image, null);
        Picasso.with(getContext())
                .load(imageUrl)
                .into(imageView);
        container.addView(imageView);
    }

    @Override
    public void showHeading(String heading) {
        TextView headingView = (TextView) mLayoutInflater.inflate(R.layout.article_heading, null);
        headingView.setText(heading);
        container.addView(headingView);
    }

    @Override
    public void showCode(String code) {
        HorizontalScrollView codeView = (HorizontalScrollView) mLayoutInflater.inflate(R.layout.article_code, null);
        TextView codeText = (TextView) codeView.findViewById(R.id.articleCode);
        codeText.setText(code);
        container.addView(codeView);
    }

    @Override
    public void showHead(String author, String date, String title) {
        authorView.setText(author);
        dateView.setText(date);
        titleView.setText(title);
    }

    @Override
    public void showLink(String text) {

    }
}
