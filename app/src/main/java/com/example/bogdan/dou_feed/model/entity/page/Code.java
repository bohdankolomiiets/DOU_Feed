package com.example.bogdan.dou_feed.model.entity.page;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.bogdan.dou_feed.R;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class Code extends PageElement {
    private String mCodeText;

    public Code(String codeText) {
        mCodeText = codeText;
    }

    @Override
    void display(LayoutInflater inflater, ViewGroup container) {
        HorizontalScrollView codeView =
                (HorizontalScrollView) inflater.inflate(R.layout.article_code, null);
        TextView codeTextView = (TextView) codeView.findViewById(R.id.articleCode);
        codeTextView.setText(mCodeText);
    }
}
