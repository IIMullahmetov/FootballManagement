package ru.kpfu.itis.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.models.Goal;
import ru.kpfu.itis.android.models.Match;

/**
 * Created by hlopu on 23.04.2018.
 */

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalViewHolder> {
    private List<Goal> goalList;
    private Context context;

    public GoalsAdapter(Context context) {
        this.context = context;
        goalList = Collections.emptyList();
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goal, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        final Goal goal = goalList.get(position);
        holder.tvTime.setText(goal.getTime());
        holder.tvBombardir.setText(goal.getNameBombardir());
        holder.tvAssistant.setText(goal.getNameAssistant());
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    class GoalViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBombardir;
        private TextView tvAssistant;
        private TextView tvTime;

        public GoalViewHolder(View itemView) {
            super(itemView);
            tvBombardir = itemView.findViewById(R.id.tv_goal_name_bombardir);
            tvAssistant = itemView.findViewById(R.id.tv_goal_name_assistant);
            tvTime = itemView.findViewById(R.id.tv_goal_minutes);
        }
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }
}