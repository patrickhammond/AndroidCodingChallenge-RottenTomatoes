package com.demo.rottentomatoes.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * An implementation of {@link BaseAdapter} which uses the new/bind pattern and
 * view holder pattern for its views.
 *
 * Inspired by/baesd on Jake Wharton's BindingAdapter:
 * https://gist.github.com/JakeWharton/5423616
 */
public abstract class BindingAdapter<T, ViewHolder> extends BaseAdapter {
    private final Context context;
    private final LayoutInflater inflater;

    public BindingAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public Context getContext() {
        return context;
    }

    @Override public abstract T getItem(int position);

    @Override public final View getView(int position, View view, ViewGroup container) {
        if (view == null) {
            view = newView(inflater, position, container);
            if (view == null) {
                throw new IllegalStateException("newView result must not be null.");
            }

            associateViewHolder(view);
        }

        bindView(getItem(position), position, view, getViewHolder(view));
        return view;
    }

    /** Create a new instance of a view for the specified position. */
    public abstract View newView(LayoutInflater inflater, int position, ViewGroup container);

    /**
     * If your ViewHolder implementation looks something like this:
     * <pre>
     * {@code
     * static class ViewHolder {
     *     final TextView textView;
     *
     *     ViewHolder(View view) {
     *         textView = (TextView) view.findViewById(R.id.textView);
     *     }
     * }
     * </pre>
     *
     * This method only needs this as its implementation:
     * <pre>
     * {@code
     * return new ViewHolder(view);
     * }
     * </pre>
     *
     * If implementations do not need/want a view holder, just return <code>null</code>.
     */
    public abstract ViewHolder buildViewHolder(View view);

    /**
     * Bind the data for the specified {@code position} to the view using a
     * {@code viewHolder} created from {@link #buildViewHolder(View)}.
     */
    public abstract void bindView(T item, int position, View view, ViewHolder viewHolder);

    @Override public final View getDropDownView(int position, View view, ViewGroup container) {
        if (view == null) {
            view = newDropDownView(inflater, position, container);
            if (view == null) {
                throw new IllegalStateException("newDropDownView result must not be null.");
            }

            associateViewHolder(view);
        }

        bindDropDownView(getItem(position), position, view, getViewHolder(view));
        return view;
    }

    private void associateViewHolder(View view) {
        ViewHolder viewHolder = buildViewHolder(view);
        view.setTag(viewHolder);
    }

    @SuppressWarnings("unchecked")
    private ViewHolder getViewHolder(View view) {
        return (ViewHolder) view.getTag();
    }

    /** Create a new instance of a drop-down view for the specified position. */
    public View newDropDownView(LayoutInflater inflater, int position, ViewGroup container) {
        return newView(inflater, position, container);
    }

    /** Bind the data for the specified {@code position} to the drop-down view. */
    public void bindDropDownView(T item, int position, View view, ViewHolder viewHolder) {
        bindView(item, position, view, viewHolder);
    }
}