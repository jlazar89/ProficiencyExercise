package com.arinspect.proficiencyexercise.widgets.recycler.holder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created Date: 27-05-2020
 * Purpose: Base holder for all the recycler view holders.
 */
public abstract class BaseHolder<M> extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }
}
