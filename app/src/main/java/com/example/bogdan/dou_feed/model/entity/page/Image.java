package com.example.bogdan.dou_feed.model.entity.page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bogdan.dou_feed.R;
import com.squareup.picasso.Picasso;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class Image extends PageElement {
    private String mUrl;
    private ImageView mImageView;

    public Image(String url) {
        mUrl = url;
    }

    @Override
    public void display(LayoutInflater inflater, ViewGroup container) {
        mImageView = (ImageView) inflater.inflate(R.layout.article_image, null);
        Picasso.with(inflater.getContext())
                .load(mUrl)
                .into(mImageView);
        container.addView(mImageView);
    }
}
