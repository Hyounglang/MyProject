package com.example.hongyonglang.mutibaseadapterlib;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hongyonglang on 15/9/1.
 */
public interface DelegationInterface<E>{

    public int getItemViewType();

    public boolean isForViewType(@NonNull List<E> items, int position);

    @NonNull
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent);

    public void onBindViewHolder(@NonNull List<E> items, int position, @NonNull ViewHolderHelper helper);
}