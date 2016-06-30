package com.example.bogdan.dou_feed.lib;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.06.16
 */
public interface PresenterCache {
    long generateId();
    <T extends Presenter> T getPresenter(long index);
    void setPresenter(long index, Presenter presenter);
}
