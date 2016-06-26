package com.example.bogdan.dou_feed.model.entity;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bogdan.dou_feed.R;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class Content extends PageElement {
    private String mText;

    public Content(String text) {
        mText = text;
    }

    @Override
    void display(LayoutInflater inflater, ViewGroup container) {
        TextView contentView = (TextView) inflater.inflate(R.layout.article_content, null);
        contentView.setText(mText);
        container.addView(contentView);
    }
}
