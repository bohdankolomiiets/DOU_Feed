package com.example.bogdan.dou_feed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.R;
import com.example.bogdan.dou_feed.di.module.ArticleViewModule;
import com.example.bogdan.dou_feed.model.entity.ArticleEntity;
import com.example.bogdan.dou_feed.presenter.ArticlePresenter;

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
        TextView textView = new TextView(getContext());
        textView.setText(content);
        container.addView(textView);
    }

    @Override
    public void showImage(String imageUrl) {

    }

    @Override
    public void showHeading(String heading) {

    }

    @Override
    public void showCode(String code) {

    }
}
