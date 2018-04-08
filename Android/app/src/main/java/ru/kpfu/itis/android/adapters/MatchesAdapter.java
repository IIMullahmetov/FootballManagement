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

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {
    private Context context;
    private List<Match> matchList;

    public MatchesAdapter(Context context) {
        this.context = context;
        matchList = Collections.emptyList();
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_item, parent, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        final Match match = matchList.get(position);
        holder.tvNameTeam1.setText(match.getTeam1());
        holder.tvNameTeam2.setText(match.getTeam2());
        holder.tvDate.setText(match.getDate());
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
        private TextView tvDate;
        private TextView tvNameTeam1;
        private TextView tvNameTeam2;
        private TextView tvNameChmpshps;

        public MatchesViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.date_championships);
            tvNameTeam1 = itemView.findViewById(R.id.match_team1);
            tvNameTeam2 = itemView.findViewById(R.id.match_team2);
            tvNameChmpshps = itemView.findViewById(R.id.name_championships);
        }
    }
}
