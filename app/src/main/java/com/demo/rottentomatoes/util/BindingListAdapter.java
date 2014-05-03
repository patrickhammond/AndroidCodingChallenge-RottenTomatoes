package com.demo.rottentomatoes.util;

import android.content.Context;

import java.util.Collections;
import java.util.List;

/**
 * Specialized binding adapter that handles some of the boilerplate associated with lists.
 * @param <T> The type of item being displayed.
 * @param <ViewHolder> The view holder type being used.
 */
public abstract class BindingListAdapter<T, ViewHolder> extends BindingAdapter<T, ViewHolder> {
    private final List<T> items;

    public BindingListAdapter(Context context, List<T> items) {
        super(context);
        this.items = Collections.unmodifiableList(items);
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
