package ru.kpfu.itis.android.fragments;

import android.app.Activity;
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

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.NewsAdapter;
import ru.kpfu.itis.android.models.Championship;
import ru.kpfu.itis.android.models.modelForList.ChampionshipInList;

/**
 * Created by hlopu on 24.04.2018.
 */

public class NewsChampionshipFragment extends Fragment {
    private Context context;
    private RecyclerView rvNews;

    public static NewsChampionshipFragment newInstance(Context context) {
        NewsChampionshipFragment newsChampionshipFragment = new NewsChampionshipFragment();
        newsChampionshipFragment.setContext(context);
        return newsChampionshipFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_championships, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ChampionshipInList championship = (ChampionshipInList) ((Activity) context).getIntent().getSerializableExtra("CHAMPIONSHIP");
        rvNews = view.findViewById(R.id.rv_news_championship);
        rvNews.setLayoutManager(new LinearLayoutManager(context));
        NewsAdapter newsAdapter = new NewsAdapter(context);
        newsAdapter.setmNewsList(championship.getNews());
        rvNews.setAdapter(newsAdapter);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
