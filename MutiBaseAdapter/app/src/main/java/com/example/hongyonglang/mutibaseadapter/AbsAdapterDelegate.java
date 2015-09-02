package com.example.hongyonglang.mutibaseadapter;

/**
 * A simple {@link AdapterDelegate} implementation that already implements {@link
 * #getItemViewType()}
 *
 * @author Hannes Dorfmann
 */
public abstract class AbsAdapterDelegate<T> implements AdapterDelegate<T> {

    /**
     * The item view type
     */
    protected int viewType;

    public AbsAdapterDelegate(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType() {
        return viewType;
    }
}