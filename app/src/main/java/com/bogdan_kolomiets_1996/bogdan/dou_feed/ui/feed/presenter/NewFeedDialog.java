package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.feed.presenter;

import android.app.Dialog;
import android.content.Context;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 04.07.16
 */
public class NewFeedDialog extends Dialog {
    private static final int LAYOUT = R.layout.new_feed_layout;

    public NewFeedDialog(Context context) {
        super(context);
        setContentView(LAYOUT);

    }

}
