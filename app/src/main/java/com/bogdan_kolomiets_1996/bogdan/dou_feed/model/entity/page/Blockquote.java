package com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class Blockquote extends PageElement {
    private String mText;

    public Blockquote(String text) {
        mText = text;
    }

    @Override
    public void display(LayoutInflater inflater, ViewGroup container) {
        TextView blockView = (TextView) inflater.inflate(R.layout.article_blockquote, null);
        blockView.setText(mText);
        container.addView(blockView);
    }
}
