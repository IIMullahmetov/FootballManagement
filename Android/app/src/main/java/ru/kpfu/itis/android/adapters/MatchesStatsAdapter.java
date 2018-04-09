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
import ru.kpfu.itis.android.models.Match;

/**
 * Created by hlopu on 08.04.2018.
 */

public class MatchesStatsAdapter extends RecyclerView.Adapter<MatchesStatsAdapter.MatchesViewHolder> {
    private Context context;
    private List<Match> matchList;

    public MatchesStatsAdapter(Context context) {
        this.context = context;
        matchList = Collections.emptyList();
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stats_match_item, parent, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        final Match match = matchList.get(position);
        holder.tvScore1.setText(match.getScore1());
        holder.tvScore2.setText(match.getScore2());
        holder.tvNameTeams.setText((match.getTeam1() + " - " + match.getTeam2()));
        holder.tvNameChmpshps.setText(match.getNameChampionships());
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {
        private TextView tvScore1;
        private TextView tvScore2;
        private TextView tvNameTeams;
        private TextView tvNameChmpshps;

        public MatchesViewHolder(View itemView) {
            super(itemView);
            tvScore1 = itemView.findViewById(R.id.goal_first_team);
            tvScore2 = itemView.findViewById(R.id.goal_second_team);
            tvNameTeams = itemView.findViewById(R.id.tv_teams);
            tvNameChmpshps = itemView.findViewById(R.id.tv_name_champshps);
        }
    }
}
