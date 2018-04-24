package ru.kpfu.itis.android.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.StatsAdapter;
import ru.kpfu.itis.android.models.Player;

/**
 * Created by hlopu on 24.04.2018.
 */

public class StatsChampionshipFragment extends Fragment {
    private Context context;
    private RecyclerView rvStats;

    public static StatsChampionshipFragment newInstansce(Context context){
        StatsChampionshipFragment statsChampionshipFragment = new StatsChampionshipFragment();
        statsChampionshipFragment.setContext(context);
        return statsChampionshipFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_championship_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStats = view.findViewById(R.id.rv_championship_stats_players);
        rvStats.setLayoutManager(new LinearLayoutManager(context));
        StatsAdapter adapter = new StatsAdapter(context);
        List<Player> players = new ArrayList<>();
        players.add(new Player("Nurislam Saitgaraev", "Tottenham", "69", "10", "0", null));
        players.add(new Player("Nurislam Saitgaraev", "Tottenham", "69", "10", "0", null));
        players.add(new Player("Nurislam Saitgaraev", "Tottenham", "69", "10", "0", null));
        adapter.setPlayers(players);
        rvStats.setAdapter(adapter);

    }

    public void setContext(Context context) {
        this.context = context;
    }
}
