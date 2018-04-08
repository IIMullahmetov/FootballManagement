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
import ru.kpfu.itis.android.adapters.MatchesAdapter;
import ru.kpfu.itis.android.models.Match;

/**
 * Created by hlopu on 08.04.2018.
 */

public class MatchesFragment extends Fragment {
    private RecyclerView rvMatches;
    private MatchesAdapter matchesAdapter;
    private Context context;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);

        rvMatches.setLayoutManager(new LinearLayoutManager(context));
        matchesAdapter = new MatchesAdapter(context);
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Tottanham", "Real", "2", "3", "Evro", "06.04.2018"));
        matches.add(new Match("Tottanham", "PSG", "5", "0", "Evro", "06.04.2018"));
        matchesAdapter.setMatchList(matches);
        rvMatches.setAdapter(matchesAdapter);

    }

    private void bind(View view) {
        rvMatches = view.findViewById(R.id.rv_matches);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
