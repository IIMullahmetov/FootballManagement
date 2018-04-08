package ru.kpfu.itis.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.MatchesStatsAdapter;
import ru.kpfu.itis.android.adapters.NewsAdapter;
import ru.kpfu.itis.android.models.Match;
import ru.kpfu.itis.android.models.News;

public class FeedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView rvNews;
    private RecyclerView rvMatches;
    private TextView tvAllNews;
    private TextView tvAllMatches;
    private Toolbar toolbar;
    private NewsAdapter newsAdapter;
    private MatchesStatsAdapter matchesStatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        bind();

        rvNews.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(FeedActivity.this);
        //TODO подгрузка с сервера
        List<News> news = new ArrayList();
        news.add(new News("url", "SAME TITLE", "TEXT TEST TEXT TEST"));
        news.add(new News("url", "SAME TITLE 2", "TEXT TEST TEXT TEST"));
        newsAdapter.setmNewsList(news);
        rvNews.setAdapter(newsAdapter);

        rvMatches.setLayoutManager(new LinearLayoutManager(this));
        matchesStatsAdapter = new MatchesStatsAdapter(this);
        //TODO подгрузка с сервера
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Tottanham", "Real", "2", "3", "Evro", "06.04.2018"));
        matchesStatsAdapter.setMatchList(matches);
        rvMatches.setAdapter(matchesStatsAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void bind(){
        rvNews = findViewById(R.id.rv_news);
        rvMatches = findViewById(R.id.rv_stats_match);
        tvAllNews = findViewById(R.id.tv_all_news);
        tvAllMatches = findViewById(R.id.tv_all_matches);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.feed);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            Intent intent = new Intent(this, MatchesActivity.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
