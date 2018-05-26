package ru.kpfu.itis.android.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import ru.kpfu.itis.android.activities.MatchActivity;
import ru.kpfu.itis.android.adapters.MatchesAdapter;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.ChampionshipTitle;
import ru.kpfu.itis.android.models.Goal;
import ru.kpfu.itis.android.models.Match;
import ru.kpfu.itis.android.models.modelForList.MatchBodyInList;

/**
 * Created by hlopu on 08.04.2018.
 */

public class MatchesFragment extends Fragment {
    private RecyclerView rvMatches;
    private MatchesAdapter matchesAdapter;
    private Context context;
    private ProgressBar progressBar;

    public static MatchesFragment newInstance(Context context) {
        MatchesFragment matchesFragment = new MatchesFragment();
        matchesFragment.setContext(context);
        return matchesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_matches, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);

        RecyclerView.ItemAnimator animator = rvMatches.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        setVisibleProgressBar(View.VISIBLE);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        requests.getMatches().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if(response.code() == 200){
                        Log.d("SIZE", String.valueOf(response.body().getItems().size()));
                        ChampionshipTitle title = new ChampionshipTitle("European Champions Cup", response.body().getItems());
                        List<ChampionshipTitle> list = new ArrayList<>();
                        list.add(title);
                        matchesAdapter = new MatchesAdapter(context, list);
                        matchesAdapter.setMatchListener(match -> {
                            Intent intent = new Intent(context, MatchActivity.class);
                            intent.putExtra("MATCH_ID", match.getId());
                            intent.putExtra("NAME_CHMP", "European Champions Cup");
                            startActivity(intent);
                        });
                        rvMatches.setAdapter(matchesAdapter);
                        matchesAdapter.notifyDataSetChanged();
                        setVisibleProgressBar(View.GONE);
                    }
                    else{
                        setVisibleProgressBar(View.GONE);
                        Log.d("Get matches", "THROW " + response.message());
                    }
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("Get matches", "THROW " + throwable.getMessage());
                    Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });

        List<MatchBodyInList> matches = new ArrayList<>();
        List<Goal> goals = new ArrayList<>();
        List<Goal> awayGoals = new ArrayList<>();
        List<Goal> homeGoals = new ArrayList<>();

        List<Goal> psgGoals = new ArrayList<>();
//        homeGoals.add(new Goal("Kane", "Lamela", "56'"));
//        homeGoals.add(new Goal("Kane", "Alli", "66'"));
//        awayGoals.add(new Goal("C.Ronaldo", "Marcelo", "44'"));
//        awayGoals.add(new Goal("Kovacic", "Modric", "64'"));
//        awayGoals.add(new Goal("M.Asensio", "L.Vazques", "70'"));
//
//        matches.add(new Match("Tottenham", "Real", "2", "3", "European Champions Cup", "06.04.2018",
//                "Bulat M.", homeGoals, awayGoals));
//        goals.add(new Goal("Kane", "Lamela", "56'"));
//        goals.add(new Goal("Kane", "Alli", "66'"));
//        goals.add(new Goal("Kane", "Lamela", "77'"));
//        goals.add(new Goal("Kane", "Lamela", "83'"));
//        goals.add(new Goal("Kane", "Lamela", "90'"));
//        matches.add(new Match("Tottenham", "PSG", "5", "0", "European Champions Cup", "09.04.2018",
//                "Timerkhanov T.", goals, psgGoals));
//        ChampionshipTitle title = new ChampionshipTitle("European Champions Cup", matches);
//        List<ChampionshipTitle> list = new ArrayList<>();
//        list.add(title);
//        matchesAdapter = new MatchesAdapter(context, list);
        rvMatches.setLayoutManager(new LinearLayoutManager(context));
//        matchesAdapter = new MatchesAdapter(context, new ArrayList<>());
//        matchesAdapter.setMatchListener(match -> {
//            Intent intent = new Intent(context, MatchActivity.class);
//            intent.putExtra("MATCH", match.getId());
//            if (matches.get(0) == match) {
//                intent.putExtra("type", 0);
//            } else {
//                intent.putExtra("type", 1);
//            }
//            startActivity(intent);
//        });
//        rvMatches.setAdapter(matchesAdapter);

    }

    private void bind(View view) {
        rvMatches = view.findViewById(R.id.rv_matches);
        progressBar = view.findViewById(R.id.pb_matches);
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
