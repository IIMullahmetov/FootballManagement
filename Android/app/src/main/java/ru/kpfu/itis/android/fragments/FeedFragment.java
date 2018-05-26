package ru.kpfu.itis.android.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.MatchesStatsAdapter;
import ru.kpfu.itis.android.adapters.NewsAdapter;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.Goal;
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
    private ProgressBar progressBar;
    private NewsAdapter newsAdapter;
    private MatchesStatsAdapter matchesStatsAdapter;
    private Context context;

    public static FeedFragment newInstance(Context context) {
        FeedFragment feedFragment = new FeedFragment();
        feedFragment.setContext(context);
        return feedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_feed, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);

        setVisibleProgressBar(View.VISIBLE);
        rvNews.setLayoutManager(new LinearLayoutManager(context));
        newsAdapter = new NewsAdapter(context);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        requests.getPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if(response.code() == 200){
                        newsAdapter.setmNewsList(response.body().getItems());
                        setVisibleProgressBar(View.GONE);
                        Log.d("Post Message", response.body().getMessage());
                    }
                    else{
                        setVisibleProgressBar(View.GONE);
                        Log.d("Get posts", "THROW " + response.message());
                    }
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("Get Posts", "THROW " + throwable.getMessage());
                    Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });

        rvNews.setAdapter(newsAdapter);
        rvMatches.setLayoutManager(new LinearLayoutManager(context));
        matchesStatsAdapter = new MatchesStatsAdapter(context);
        //TODO подгрузка с сервера
        List<Match> matches = new ArrayList<>();
        List<Goal> goals = new ArrayList<>();
//        goals.add(new Goal("NURIK", "TOLYA", "56'"));
//        goals.add(new Goal("NURIK", "TOLYA", "56'"));
        matches.add(new Match("Tottenham Hotspur", "Real Madrid", "2", "3", "European Champions Cup", "06.04.2018",
                "Bulat Mot", goals, goals));
        matchesStatsAdapter.setMatchList(matches);
        rvMatches.setAdapter(matchesStatsAdapter);

        tvAllMatches.setOnClickListener(v->{
            MatchesFragment fragment = MatchesFragment.newInstance(context);
            FragmentManager fragmentManager = getFragmentManager();
            if (fragment != null)  fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
        });
    }

    private void bind(View view) {
        rvNews = view.findViewById(R.id.rv_news);
        rvMatches = view.findViewById(R.id.rv_stats_match);
        tvAllNews = view.findViewById(R.id.tv_all_news);
        tvAllMatches = view.findViewById(R.id.tv_all_matches);
        progressBar = view.findViewById(R.id.pb_feed);
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
