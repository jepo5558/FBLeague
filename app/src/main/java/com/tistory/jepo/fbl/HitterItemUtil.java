package com.tistory.jepo.fbl;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class HitterItemUtil extends DiffUtil.ItemCallback<Hitter> {
    @Override
    public boolean areItemsTheSame(@NonNull Hitter oldItem, @NonNull Hitter newItem) {
        if (oldItem.getName().equals(newItem.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Hitter oldItem, @NonNull Hitter newItem) {
        return false;
    }
}
