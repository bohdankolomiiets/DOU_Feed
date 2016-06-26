package com.example.bogdan.dou_feed.model.entity.page;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bogdan.dou_feed.R;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class Heading extends PageElement {
    private String mText;

    public Heading(String text) {
        mText =  text;
    }

    @Override
    public void display(LayoutInflater inflater, ViewGroup container) {
        TextView headingView = (TextView) inflater.inflate(R.layout.article_heading, null);
        headingView.setText(mText);
        container.addView(headingView);
    }
}
