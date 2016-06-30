package com.example.bogdan.dou_feed.lib;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.06.16
 */
public class PresenterCache {
    private static PresenterCache instance = null;
    private SimpleArrayMap<String, Presenter> presenters;

    private PresenterCache() {

    }

    public static PresenterCache getInstance() {
        if (instance == null) {
            instance = new PresenterCache();
        }

        return instance;
    }


    public final <T extends Presenter> T getPresenter(String who, PresenterFactory<T> factory) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }
        T p = null;
        try {
            p = (T) presenters.get(who);
        } catch (ClassCastException e) {
            Log.w("PresenterActivity", "Duplicate Presenter " +
                    "tag identified: " + who + ". This could " +
                    "cause issues with state.");
        }
        if (p == null) {
            p = factory.createPresenter();
            presenters.put(who, p);
        }
        return p;
    }

    public final void removePresenter(String who) {
        if (presenters != null) {
            presenters.remove(who);
        }
    }


}
