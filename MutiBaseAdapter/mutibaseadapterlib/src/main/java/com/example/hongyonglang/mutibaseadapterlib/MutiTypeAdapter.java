package com.example.hongyonglang.mutibaseadapterlib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hongyonglang on 15/9/1.
 */
public class MutiTypeAdapter<E> extends RecyclerView.Adapter<ViewHolderHelper>{

    private List<E> mDatas;

    private Context mContext;

    private DelegationManager<E> mManager;

    public MutiTypeAdapter(Context mContext,List<E> mDatas) {
        mManager = new DelegationManager<>();
        this.mDatas   = mDatas;
        this.mContext = mContext;
    }

    public void addTypeDelegation(DelegationInterface<E> delegation){
        mManager.addDelegate(delegation);
    }

    @Override
    public int getItemViewType(int position) {
        return mManager.getItemViewType(mDatas,position);
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        return mManager.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolderHelper helper, int position) {
        mManager.onBindViewHolder(mDatas,position,helper);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
