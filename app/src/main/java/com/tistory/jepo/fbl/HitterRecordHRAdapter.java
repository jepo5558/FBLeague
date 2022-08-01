package com.tistory.jepo.fbl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class HitterRecordHRAdapter extends ListAdapter<Hitter, HitterRecordHRAdapter.ViewHolder> {

    protected HitterRecordHRAdapter(@NonNull DiffUtil.ItemCallback<Hitter> diffCallback) {
        super(diffCallback);
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

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hitterName;
        private TextView hitterRecord;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hitterName = itemView.findViewById(R.id.playerNameTextView);
            hitterRecord = itemView.findViewById(R.id.winTextView);
        }

        private void bind(Hitter hitter) {
            hitterName.setText(hitter.getName());
            hitterRecord.setText(String.valueOf(hitter.getRecord().getHomerun()));
        }
    }
}
