package ru.kpfu.itis.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.models.Player;

/**
 * Created by hlopu on 24.04.2018.
 */

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {
    private Context context;
    private List<Player> players;

    public StatsAdapter(Context context) {
        this.context = context;
        players = Collections.emptyList();
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stats_player, parent, false);
        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        final Player player = players.get(position);
        holder.tvNumber.setText(player.getNumber());
        holder.tvName.setText(player.getName());
        holder.tvGoals.setText(player.getCountGoals());
        holder.tvMatches.setText(player.getCountMatches());
        holder.tvClub.setText(player.getTeam());

        Glide.with(context)
                .load(R.drawable.real_madrid)
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class StatsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber;
        private ImageView ivPhoto;
        private TextView tvName;
        private TextView tvClub;
        private TextView tvMatches;
        private TextView tvGoals;

        public StatsViewHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_stats_number);
            ivPhoto = itemView.findViewById(R.id.iv_stats_image);
            tvName = itemView.findViewById(R.id.tv_stats_name);
            tvClub = itemView.findViewById(R.id.tv_stats_club);
            tvMatches = itemView.findViewById(R.id.tv_stats_matches);
            tvGoals = itemView.findViewById(R.id.tv_stats_goals);
        }
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
