package com.tistory.jepo.fbl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PitcherRecordEraAdapter extends ListAdapter<Pitcher, PitcherRecordEraAdapter.ViewHolder> {

    private OnPitcherLongClickListener listener;

    protected PitcherRecordEraAdapter(@NonNull DiffUtil.ItemCallback diffCallback, OnPitcherLongClickListener l) {
        super(diffCallback);
        listener = l;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new PitcherRecordEraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClicked(getItem(position));
                return true;
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pitcherName;
        private TextView pitcherRecord;
        private View rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.rootView);
            pitcherName = itemView.findViewById(R.id.playerNameTextView);
            pitcherRecord = itemView.findViewById(R.id.winTextView);
        }

        private void bind(Pitcher pitcher) {
            pitcherName.setText(pitcher.getName());
            pitcherRecord.setText(pitcher.getRecord().getEra());
            return;
        }

    }
}
