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
import ru.kpfu.itis.android.models.Championship;

/**
 * Created by hlopu on 08.04.2018.
 */

public class ChampionshipAdapter extends RecyclerView.Adapter<ChampionshipAdapter.ChampionshipViewHolder> {
    private Context context;
    private List<Championship> championships;
    private ChampionshipListener championshipListener;

    public ChampionshipAdapter(Context context) {
        this.context = context;
        championships = Collections.emptyList();
    }

    @NonNull
    @Override
    public ChampionshipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.championship_item, parent, false);
        return new ChampionshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionshipViewHolder holder, int position) {
        final Championship championship = championships.get(position);
        holder.tvName.setText(championship.getName());
        holder.itemView.setOnClickListener(v -> {
            championshipListener.onClickListener(championship);
        });
    }

    @Override
    public int getItemCount() {
        return championships.size();
    }


    public class ChampionshipViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public ChampionshipViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_champ_name);
        }
    }

    public void setChampionships(List<Championship> championships) {
        this.championships = championships;
    }

    public interface ChampionshipListener {
        void onClickListener(Championship championship);
    }

    public void setChampionshipListener(ChampionshipListener championshipListener) {
        this.championshipListener = championshipListener;
    }
}
