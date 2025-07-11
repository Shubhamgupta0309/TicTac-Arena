package com.shubham.tictacarena.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shubham.tictacarena.R;
import com.shubham.tictacarena.models.Pattern;
import java.util.List;

public class PatternAdapter extends RecyclerView.Adapter<PatternAdapter.PatternViewHolder> {

    private final List<Pattern> patterns;
    private final OnPatternClickListener listener;

    public PatternAdapter(List<Pattern> patterns, OnPatternClickListener listener) {
        this.patterns = patterns;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatternViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pattern, parent, false);
        return new PatternViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatternViewHolder holder, int position) {
        Pattern pattern = patterns.get(position);
        holder.patternName.setText(pattern.getName());
        holder.patternRule.setText(pattern.getRule());
        holder.itemView.setOnClickListener(v -> listener.onPatternClick(pattern));
    }

    @Override
    public int getItemCount() {
        return patterns.size();
    }

    static class PatternViewHolder extends RecyclerView.ViewHolder {
        TextView patternName;
        TextView patternRule;

        public PatternViewHolder(@NonNull View itemView) {
            super(itemView);
            patternName = itemView.findViewById(R.id.patternName);
            patternRule = itemView.findViewById(R.id.patternRule);
        }
    }

    public interface OnPatternClickListener {
        void onPatternClick(Pattern pattern);
    }
}