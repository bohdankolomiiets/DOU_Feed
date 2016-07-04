package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.article.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 29.06.16
 */
public class ImageDialog extends Dialog {
  private static final int LAYOUT = R.layout.image_viewer_layout;

  @BindView(R.id.viewerImage)
  ImageView imageView;

  public ImageDialog(Context context) {
    super(context, R.style.DialogTheme);
    setContentView(LAYOUT);
    ButterKnife.bind(this);

  }

  public void setImage(Bitmap image) {
    imageView.setImageBitmap(image);
  }
}
