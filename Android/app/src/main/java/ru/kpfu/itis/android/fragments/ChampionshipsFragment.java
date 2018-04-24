package ru.kpfu.itis.android.fragments;

import android.content.Context;
import android.content.Intent;
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
import ru.kpfu.itis.android.activities.ChampionshipActivity;
import ru.kpfu.itis.android.adapters.ChampionshipAdapter;
import ru.kpfu.itis.android.models.Championship;
import ru.kpfu.itis.android.models.News;

/**
 * Created by hlopu on 08.04.2018.
 */

public class ChampionshipsFragment extends Fragment{
    private RecyclerView rvChampionships;
    private ChampionshipAdapter championshipAdapter;
    private Context context;

    public static ChampionshipsFragment newInstance(Context context) {
        ChampionshipsFragment championshipsFragment = new ChampionshipsFragment();
        championshipsFragment.setContext(context);
        return championshipsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_championship, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);

        rvChampionships.setLayoutManager(new LinearLayoutManager(context));
        championshipAdapter = new ChampionshipAdapter(context);
        List<Championship> chmpshps = new ArrayList<>();
        List<News> news = new ArrayList<>();
        news.add(new News("url", "Выиграет ли Реал третий кубок Лиги Чемпионов подряд?", "TEXT TEST TEXT TEST"));
        news.add(new News("url", "Делаем ставки, господа!", "TEXT TEST TEXT TEST"));
        chmpshps.add(new Championship("РФПЛ", news));
        chmpshps.add(new Championship("UEFA", news));
        chmpshps.add(new Championship("Evro", news));
        championshipAdapter.setChampionships(chmpshps);
        championshipAdapter.setChampionshipListener(championship -> {
            Intent intent = new Intent(context, ChampionshipActivity.class);
            intent.putExtra("CHAMPIONSHIP", championship);
            startActivity(intent);
        });
        rvChampionships.setAdapter(championshipAdapter);

    }

    private void bind(View view) {
        rvChampionships = view.findViewById(R.id.rv_championships);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
