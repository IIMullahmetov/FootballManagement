package ru.kpfu.itis.android.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.PlayerDetail;
import ru.kpfu.itis.android.models.TeamDetail;

public class PlayerActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageView ivPhoto;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvTeam;
    private TextView tvMatchesCount;
    private TextView tvWinsCount;
    private TextView tvGoalsCount;
    private TextView tvAssistsCount;
    private TextView tvBirth;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        bind();
        int idPlayer = getIntent().getIntExtra("ID_PLAYER", 0);
        setVisibleProgressBar(View.VISIBLE);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();

        requests.getPlayer(idPlayer).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.code() == 200) {
                        Log.d("get Player", String.valueOf(response.body().getId()));
                        fillFields(response.body());
                        setVisibleProgressBar(View.GONE);
                    } else {
                        setVisibleProgressBar(View.GONE);
                        Log.d("get Player", "THROW " + response.message() + " " + response.code());
                        Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();


                    }
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("get Player", "THROW " + throwable.getMessage());
                    Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void bind() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Игрок");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvName = findViewById(R.id.tv_player_name);
        progressBar = findViewById(R.id.pb_player);
        ivPhoto = findViewById(R.id.iv_player_image);
        tvAge = findViewById(R.id.tv_player_age);
        tvMatchesCount = findViewById(R.id.tv_player_matches_count);
        tvWinsCount = findViewById(R.id.tv_player_wins_count);
        tvGoalsCount = findViewById(R.id.tv_player_goals_count);
        tvAssistsCount = findViewById(R.id.tv_player_assists_count);
        tvBirth = findViewById(R.id.tv_player_birthday);
        tvTeam = findViewById(R.id.tv_player_team);
    }

    private void fillFields(PlayerDetail player) {
        tvName.setText(player.getFirstName() + " " + player.getLastName());
        Glide.with(this)
                .load(SportApiRequests.DOWNLOAD_IMAGE + player.getImage())
                .apply(RequestOptions.fitCenterTransform())
                .into(ivPhoto);
        tvAge.setText("Полных лет: " + player.getAge());
        tvTeam.setText("Команда: " + player.getTeam());
        tvTeam.setPaintFlags(tvTeam.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTeam.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeamActivity.class);
            intent.putExtra("ID_TEAM", player.getTeamId());
            startActivity(intent);
        });
        tvBirth.setText("День Рождения: " + player.getBirthDt());
        tvAssistsCount.setText("Ассисты: " + player.getAssistsCount());
        tvGoalsCount.setText("Забитые голы: " + player.getGoalsCount());
        tvWinsCount.setText("Выигранных матчей: " + player.getWinsCount());
        tvMatchesCount.setText("Сыграно матчей " + player.getMatchesCount());

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
