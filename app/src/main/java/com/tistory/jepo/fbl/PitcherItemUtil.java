package com.tistory.jepo.fbl;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class PitcherItemUtil extends DiffUtil.ItemCallback<Pitcher> {
    @Override
    public boolean areItemsTheSame(@NonNull Pitcher oldItem, @NonNull Pitcher newItem) {
        if (oldItem.getName().equals(newItem.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Pitcher oldItem, @NonNull Pitcher newItem) {
        return false;
    }
}
