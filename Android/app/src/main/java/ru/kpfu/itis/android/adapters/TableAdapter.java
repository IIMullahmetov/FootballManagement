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
import ru.kpfu.itis.android.activities.TeamActivity;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.TableItem;

/**
 * Created by Bulat on 24.04.2018 at 20:12.
 */
public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private Context context;
    private List<TableItem> tables;

    public TableAdapter(Context context) {
        this.context = context;
        tables = Collections.emptyList();
    }

    @NonNull
    @Override
    public TableAdapter.TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_item, parent, false);
        return new TableAdapter.TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.TableViewHolder holder, int position) {
        final TableItem tableItem = tables.get(position);
        Glide.with(context).load(SportApiRequests.DOWNLOAD_IMAGE + tableItem.getIcon())
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.ivLogo);

        holder.tvName.setText(tableItem.getTeamName());
        holder.tvGames.setText(tableItem.getGames());
        holder.tvWins.setText(tableItem.getWins());

        holder.tvDraws.setText(tableItem.getDraws());
        holder.tvLosses.setText(tableItem.getLosses());
        holder.tvPoints.setText(tableItem.getPoints());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TeamActivity.class);
            intent.putExtra("ID_TEAM", tableItem.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public void setTable(List<TableItem> tables) {
        this.tables = tables;
        notifyDataSetChanged();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivLogo;
        private TextView tvName;
        private TextView tvGames;
        private TextView tvWins;
        private TextView tvDraws;
        private TextView tvLosses;
        private TextView tvPoints;


        public TableViewHolder(View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.logo);
            tvName = itemView.findViewById(R.id.team);
            tvGames = itemView.findViewById(R.id.games);
            tvWins = itemView.findViewById(R.id.wins);
            tvDraws = itemView.findViewById(R.id.draws);
            tvLosses = itemView.findViewById(R.id.losses);
            tvPoints = itemView.findViewById(R.id.points);


        }
    }


}