package com.example.bogdan.dou_feed.model.entity;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 26.06.16
 */
public abstract class PageElement {

    abstract void display(LayoutInflater inflater, ViewGroup container);
}
