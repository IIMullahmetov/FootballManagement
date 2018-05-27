package ru.kpfu.itis.android.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.PlayerTeamAdapter;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.TeamDetail;

public class TeamActivity extends AppCompatActivity {
    private TextView tvName;
    private ImageView ivPhoto;
    private TextView tvCoach;
    private ProgressBar progressBar;
    private RecyclerView rvPlayers;
    private PlayerTeamAdapter playerTeamAdapter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        bind();

        rvPlayers.setLayoutManager(new LinearLayoutManager(this));
        playerTeamAdapter = new PlayerTeamAdapter(this);
        rvPlayers.setAdapter(playerTeamAdapter);

        setVisibleProgressBar(View.VISIBLE);
        int idTeam = getIntent().getIntExtra("ID_TEAM", 0);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        requests.getTeam(idTeam).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.code() == 200) {
                        Log.d("get Team", String.valueOf(response.body().getId()));
                        fillFields(response.body());
                        setVisibleProgressBar(View.GONE);
                    } else {
                        setVisibleProgressBar(View.GONE);
                        Log.d("get Team", "THROW " + response.message() + " " + response.code());
                        Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();


                    }
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("get Team", "THROW " + throwable.getMessage());
                    Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void bind() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Команда");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvName = findViewById(R.id.tv_team_name);
        progressBar = findViewById(R.id.pb_team);
        ivPhoto = findViewById(R.id.iv_team_image);
        tvCoach = findViewById(R.id.tv_team_coach);
        rvPlayers = findViewById(R.id.rv_team_players);
    }

    private void fillFields(TeamDetail teamDetail) {
        tvName.setText(teamDetail.getName());
        if (teamDetail.getPlayerDetails().size() > 5)
            tvCoach.setText("Тренер: "+teamDetail.getPlayerDetails().get(4).getFirstName() +" "+ teamDetail.getPlayerDetails().get(2).getLastName());
        Glide.with(this)
                .load(SportApiRequests.DOWNLOAD_IMAGE + teamDetail.getLogo())
                .apply(RequestOptions.fitCenterTransform())
                .into(ivPhoto);
        playerTeamAdapter.setPlayers(teamDetail.getPlayerDetails());
        playerTeamAdapter.notifyDataSetChanged();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
