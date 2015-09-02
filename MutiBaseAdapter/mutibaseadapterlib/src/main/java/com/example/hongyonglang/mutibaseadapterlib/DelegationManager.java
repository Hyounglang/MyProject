package com.example.hongyonglang.mutibaseadapterlib;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import java.util.List;

public class DelegationManager<E> {

    SparseArrayCompat<DelegationInterface<E>> delegates = new SparseArrayCompat();

    public DelegationManager<E> addDelegate(@NonNull DelegationInterface<E> delegate) {
        return addDelegate(delegate, false);
    }

    public DelegationManager<E> addDelegate(@NonNull DelegationInterface<E> delegate,
                                                  boolean allowReplacingDelegate) {

        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        }

        int viewType = delegate.getItemViewType();
        if (!allowReplacingDelegate && delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An AdapterDelegate is already registered for the viewType = " + viewType
                            + ". Already registered AdapterDelegate is " + delegates.get(viewType));
        }

        delegates.put(viewType, delegate);

        return this;
    }

    public DelegationManager<E> removeDelegate(@NonNull DelegationInterface<E> delegate) {

        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null");
        }

        DelegationInterface<E> queried = delegates.get(delegate.getItemViewType());
        if (queried != null && queried == delegate) {
            delegates.remove(delegate.getItemViewType());
        }
        return this;
    }

    public DelegationManager<E> removeDelegate(int viewType) {
        delegates.remove(viewType);
        return this;
    }

    public int getItemViewType(@NonNull List<E> items, int position) {

        if (items == null) {
            throw new NullPointerException("Items datasource is null!");
        }

        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            DelegationInterface<E> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return delegate.getItemViewType();
            }
        }

        throw new IllegalArgumentException(
                "No AdapterDelegate added that matches position=" + position + " in data source");
    }

    @NonNull
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        DelegationInterface<E> delegate = delegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        ViewHolderHelper vh = delegate.onCreateViewHolder(parent);
        if (vh == null) {
            throw new NullPointerException(
                    "ViewHolder returned from AdapterDelegate " + delegate + " for ViewType =" + viewType
                            + " is null!");
        }
        return vh;
    }

    public void onBindViewHolder(@NonNull List<E> items, int position, @NonNull ViewHolderHelper helper) {

        DelegationInterface<E> delegate = delegates.get(helper.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException(
                    "No AdapterDelegate added for ViewType " + helper.getItemViewType());
        }

        delegate.onBindViewHolder(items, position, helper);
    }
}
