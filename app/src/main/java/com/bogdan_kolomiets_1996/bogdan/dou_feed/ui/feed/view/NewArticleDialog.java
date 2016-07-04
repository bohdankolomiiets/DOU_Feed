package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.view;

import android.app.Dialog;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.widget.TextView;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 04.07.16
 */
public class NewArticleDialog extends Dialog {
  private static final int LAYOUT = R.layout.new_feed_layout;

  @BindView(R.id.add_article_redactor)
  TextView redactorLink;

  @BindView(R.id.add_article_mail)
  TextView mailTo;

  public NewArticleDialog(Context context) {
    super(context);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(LAYOUT);
    ButterKnife.bind(this);
    redactorLink.setMovementMethod(LinkMovementMethod.getInstance());
    mailTo.setMovementMethod(LinkMovementMethod.getInstance());
  }

}
