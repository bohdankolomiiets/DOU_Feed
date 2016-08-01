package com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;
import com.squareup.picasso.Picasso;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public class Image extends PageElement implements OnClickListener {
  private String mUrl;
  private ImageView mImageView;
  private Context mContext;

  public Image(String url) {
    mUrl = url;
  }

  @Override
  public void display(LayoutInflater inflater, ViewGroup container) {
    mContext = inflater.getContext();

    mImageView = (ImageView) inflater.inflate(R.layout.article_image, null);
    Picasso.with(mContext)
        .load(mUrl)
        .into(mImageView);
    container.addView(mImageView);
    mImageView.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v == mImageView) {
      if (hasImageClickListener(mContext)) {

        ((OnImageClickListener) mContext).onImageClick(mImageView);
      }
    }
  }

  private boolean hasImageClickListener(Context context) {
    return context instanceof OnImageClickListener;
  }

  public interface OnImageClickListener {
    void onImageClick(ImageView imageView);
  }
}
