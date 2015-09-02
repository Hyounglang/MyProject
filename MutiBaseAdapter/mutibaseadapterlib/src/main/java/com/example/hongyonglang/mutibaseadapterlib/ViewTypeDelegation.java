package com.example.hongyonglang.mutibaseadapterlib;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 继承delegationInterface
 * Created by hongyonglang on 15/9/1.
 */
public abstract class ViewTypeDelegation<E> implements DelegationInterface<E> , View.OnClickListener{

    private int layoutID;

    private int VIEW_TYPE;

    public ViewTypeDelegation(int layoutID,int VIEW_TYPE){
        this.layoutID = layoutID;
        this.VIEW_TYPE = VIEW_TYPE;
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE;
    }

    @Override
    public boolean isForViewType(@NonNull List<E> items, int position) {
        if (items.get(position) instanceof MutiTypeClass) {
            return ((MutiTypeClass)(items.get(position))).type == getItemViewType();
        }else
            return true;

    }

    @Override
    public void onClick(View v) {
        onItemClick(v, (Integer) v.getTag());
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
        view.setOnClickListener(this);
        ViewHolderHelper vh = new ViewHolderHelper(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull List<E> items, int position, @NonNull ViewHolderHelper helper) {
        helper.itemView.setTag(position);
        E item = items.get(position);
        doYours(helper, item);
    }

    protected abstract void doYours(ViewHolderHelper helper, E item);

    protected abstract void onItemClick(View v,int position);
}
