package com.example.bogdan.dou_feed.lib;

import android.os.Bundle;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.06.16
 */
public interface Presenter<T> {
    void onCreate(Bundle bundle);
    void onSaveInstanceState(Bundle bundle);
    void onDestroy();
    void bindView(T view);
    void unbindView();
}
