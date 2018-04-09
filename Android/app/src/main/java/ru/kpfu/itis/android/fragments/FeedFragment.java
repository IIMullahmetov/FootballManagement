package ru.kpfu.itis.android.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.MatchesStatsAdapter;
import ru.kpfu.itis.android.adapters.NewsAdapter;
import ru.kpfu.itis.android.models.Match;
import ru.kpfu.itis.android.models.News;

/**
 * Created by hlopu on 08.04.2018.
 */

public class FeedFragment extends Fragment {
    private RecyclerView rvNews;
    private RecyclerView rvMatches;
    private TextView tvAllNews;
    private TextView tvAllMatches;
    private Toolbar toolbar;
    private NewsAdapter newsAdapter;
    private MatchesStatsAdapter matchesStatsAdapter;
    private Context context;

    public static FeedFragment newInstance(Context context){
        FeedFragment feedFragment = new FeedFragment();
        feedFragment.setContext(context);
        return feedFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);

        rvNews.setLayoutManager(new LinearLayoutManager(context));
        newsAdapter = new NewsAdapter(context);
        //TODO подгрузка с сервера
        List<News> news = new ArrayList();
        news.add(new News("url", "SAME TITLE", "TEXT TEST TEXT TEST"));
        news.add(new News("url", "SAME TITLE 2", "TEXT TEST TEXT TEST"));
        newsAdapter.setmNewsList(news);
        rvNews.setAdapter(newsAdapter);

        rvMatches.setLayoutManager(new LinearLayoutManager(context));
        matchesStatsAdapter = new MatchesStatsAdapter(context);
        //TODO подгрузка с сервера
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Tottenham Hotspur", "Real Madrid", "2", "3", "European Champions Cup", "06.04.2018"));
        matchesStatsAdapter.setMatchList(matches);
        rvMatches.setAdapter(matchesStatsAdapter);

    }
    private void bind(View view){
        rvNews = view.findViewById(R.id.rv_news);
        rvMatches = view.findViewById(R.id.rv_stats_match);
        tvAllNews = view.findViewById(R.id.tv_all_news);
        tvAllMatches = view.findViewById(R.id.tv_all_matches);
    }
    public void setContext(Context context) {
        this.context = context;
    }
}
