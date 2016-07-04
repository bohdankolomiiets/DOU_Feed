package com.bogdan_kolomiets_1996.bogdan.dou_feed.ui.common;

import android.content.Context;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface View {

    void showLoading();

    void hideLoading();

    void showError(String message);

    Context getDouContext();
}
