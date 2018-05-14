package ru.kpfu.itis.android.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.GoalsAdapter;
import ru.kpfu.itis.android.models.Match;

public class MatchActivity extends AppCompatActivity {
    private RecyclerView rvGoalsTeam1;
    private RecyclerView rvGoalsTeam2;
    private TextView tvNameTeam1;
    private TextView tvNameTeam2;
    private TextView tvScore;
    private TextView tvChampionship;
    private TextView tvReferee;
    private TextView tvDate;
    private ImageView ivTeam1;
    private ImageView ivTeam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        bind();


        Match match = getIntent().getParcelableExtra("MATCH");

        tvNameTeam1.setText(match.getTeam1());
        tvNameTeam2.setText(match.getTeam2());
        tvScore.setText((match.getScore1() + " : " + match.getScore2()));
        tvChampionship.setText(match.getNameChampionships());
        tvReferee.setText(match.getReferee());
        tvDate.setText(match.getDate());

        rvGoalsTeam1.setLayoutManager(new LinearLayoutManager(this));
        GoalsAdapter goalsAdapter = new GoalsAdapter(MatchActivity.this);
        goalsAdapter.setGoalList(match.getGoalsTeam1());
        rvGoalsTeam1.setAdapter(goalsAdapter);
        rvGoalsTeam2.setLayoutManager(new LinearLayoutManager(this));
        GoalsAdapter goalsAdapter2 = new GoalsAdapter(MatchActivity.this);
        goalsAdapter2.setGoalList(match.getGoalsTeam2());
        rvGoalsTeam2.setAdapter(goalsAdapter2);

        switch (getIntent().getIntExtra("type", 0)) {
            case 0:
                Glide.with(this)
                        .load(R.drawable.tottenham_hotspur)
                        .apply(RequestOptions.fitCenterTransform())
                        .into(ivTeam1);
                Glide.with(this)
                        .load(R.drawable.real_madrid)
                        .apply(RequestOptions.fitCenterTransform())
                        .into(ivTeam2);
                break;
            case 1:
                Glide.with(this)
                        .load(R.drawable.tottenham_hotspur)
                        .apply(RequestOptions.fitCenterTransform())
                        .into(ivTeam1);
                Glide.with(this)
                        .load(R.drawable.psg)
                        .apply(RequestOptions.fitCenterTransform())
                        .into(ivTeam2);
                break;
        }


    }

    public void bind() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Матч");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvGoalsTeam1 = findViewById(R.id.rv_match_goals_team1);
        rvGoalsTeam2 = findViewById(R.id.rv_match_goals_team2);
        tvNameTeam1 = findViewById(R.id.tv_match_team1);
        tvNameTeam2 = findViewById(R.id.tv_match_team2);
        tvScore = findViewById(R.id.tv_match_score);
        tvChampionship = findViewById(R.id.tv_match_championship);
        tvReferee = findViewById(R.id.tv_match_referee);
        tvDate = findViewById(R.id.tv_match_date);
        ivTeam1 = findViewById(R.id.iv_match_photo_team1);
        ivTeam2 = findViewById(R.id.iv_match_photo_team2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
