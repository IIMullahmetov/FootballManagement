package ru.kpfu.itis.android.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.activities.ChampionshipActivity;
import ru.kpfu.itis.android.adapters.ChampionshipAdapter;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.Championship;
import ru.kpfu.itis.android.models.News;

/**
 * Created by hlopu on 08.04.2018.
 */

public class ChampionshipsFragment extends Fragment {
    private RecyclerView rvChampionships;
    private ChampionshipAdapter championshipAdapter;
    private Context context;
    private ProgressBar progressBar;

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

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);

        setVisibleProgressBar(View.VISIBLE);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        rvChampionships.setLayoutManager(new LinearLayoutManager(context));
        championshipAdapter = new ChampionshipAdapter(context);
        requests.getChampionships(20).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.code() == 200) {
                        Log.d("CHMPS", String.valueOf(response.body().getItems().size()));
                        championshipAdapter.setChampionships(response.body().getItems());
                        championshipAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400) {
                        Log.d("get chmp", "THROW " + response.code());
                        Toast.makeText(context, "Не удалось загрузить", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("get chmp", "Code " + response.code());
                    }
                    setVisibleProgressBar(View.GONE);
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("Get chmp", "THROW " + throwable.getMessage());
                    Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });

        List<Championship> chmpshps = new ArrayList<>();
        List<News> news = new ArrayList<>();
        news.add(new News("Выиграет ли Реал третий кубок Лиги Чемпионов подряд?", "TEXT TEST TEXT TEST", "url"));
        news.add(new News("Делаем ставки, господа!", "TEXT TEST TEXT TEST", String.valueOf(R.drawable.real)));
//        chmpshps.add(new Championship("РФПЛ", R.drawable.russia, news));
//        chmpshps.add(new Championship("UEFA", R.drawable.europe, news));
//        chmpshps.add(new Championship("Euro", R.drawable.europe, news));
//        championshipAdapter.setChampionships(chmpshps);
        championshipAdapter.setChampionshipListener(championship -> {
            championship.setNews(news);
            Intent intent = new Intent(context, ChampionshipActivity.class);
            intent.putExtra("CHAMPIONSHIP", championship);
            startActivity(intent);
        });
        rvChampionships.setAdapter(championshipAdapter);

    }

    private void bind(View view) {
        rvChampionships = view.findViewById(R.id.rv_championships);
        progressBar = view.findViewById(R.id.pb_championships);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setVisibleProgressBar(int visibility) {
        switch (visibility) {
            case View.VISIBLE:
                progressBar.setVisibility(visibility);
                break;
            case View.GONE:
                progressBar.setVisibility(visibility);
                break;
        }
    }
}
