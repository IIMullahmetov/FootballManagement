package ru.kpfu.itis.android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.fragments.ChampionshipsFragment;
import ru.kpfu.itis.android.fragments.FeedFragment;
import ru.kpfu.itis.android.fragments.MatchesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    Context context = this;
    NavigationView navigationView;
    SharedPreferences sharedPref;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        Fragment fragment = FeedFragment.newInstance(MainActivity.this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.profile_photo);


    }

    private void bind() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.feed);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!sharedPref.getString("profile", "").equals("")) {

            Glide.with(context)
                    .load(Uri.parse(sharedPref.getString("profile", "")))
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_feed:
                fragment = FeedFragment.newInstance(MainActivity.this);
                toolbar.setTitle(R.string.feed);
                break;
            case R.id.nav_matches:
                fragment = MatchesFragment.newInstance(MainActivity.this);
                toolbar.setTitle(R.string.matches);
                break;
            case R.id.nav_championships:
                fragment = ChampionshipsFragment.newInstance(MainActivity.this);
                toolbar.setTitle(R.string.championships);
                break;
            case R.id.nav_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                fragment = FeedFragment.newInstance(MainActivity.this);
                toolbar.setTitle(R.string.feed);
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
