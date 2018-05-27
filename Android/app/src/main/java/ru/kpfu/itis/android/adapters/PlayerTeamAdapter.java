package ru.kpfu.itis.android.adapters;

import android.content.Context;
import android.content.Intent;
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
import ru.kpfu.itis.android.activities.PlayerActivity;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.PlayerMatch;

public class PlayerTeamAdapter extends RecyclerView.Adapter<PlayerTeamAdapter.TeamPlayerViewHolder> {
    private Context context;
    private List<PlayerMatch> players;

    public PlayerTeamAdapter(Context context) {
        this.context = context;
        this.players = Collections.emptyList();
    }

    @NonNull
    @Override
    public TeamPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_team_item, parent, false);
        return new TeamPlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamPlayerViewHolder holder, int position) {
        final PlayerMatch player = players.get(position);
        Glide.with(context)
                .load(SportApiRequests.DOWNLOAD_IMAGE + player.getImage())
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.ivPlayerTeam);
        holder.tvPlayerName.setText(player.getFirstName()+" "+player.getLastName());

        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra("ID_PLAYER", player.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class TeamPlayerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlayerTeam;
        TextView tvPlayerName;

        public TeamPlayerViewHolder(View itemView) {
            super(itemView);
            ivPlayerTeam = itemView.findViewById(R.id.iv_team_player);
            tvPlayerName = itemView.findViewById(R.id.tv_team_player_name);
        }
    }

    public void setPlayers(List<PlayerMatch> players) {
        this.players = players;
    }
}
