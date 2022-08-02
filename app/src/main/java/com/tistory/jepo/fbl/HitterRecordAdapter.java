package com.tistory.jepo.fbl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class HitterRecordAdapter extends ListAdapter<Hitter, HitterRecordAdapter.ViewHolder> {

    private OnHitterLongClickListener listener;
    protected HitterRecordAdapter(@NonNull DiffUtil.ItemCallback<Hitter> diffCallback, OnHitterLongClickListener l) {
        super(diffCallback);
        listener = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClicked(getItem(position));
                return false;
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hitterName;
        private TextView hitterRecord;
        private View rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hitterName = itemView.findViewById(R.id.playerNameTextView);
            hitterRecord = itemView.findViewById(R.id.winTextView);
            rootView = itemView.findViewById(R.id.rootView);
        }

        private void bind(Hitter hitter) {
            hitterName.setText(hitter.getName());
            hitterRecord.setText(hitter.getRecord().getAvg());
        }
    }
}
