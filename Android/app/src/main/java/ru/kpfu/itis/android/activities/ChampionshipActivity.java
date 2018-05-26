package ru.kpfu.itis.android.activities;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.fragments.NewsChampionshipFragment;
import ru.kpfu.itis.android.fragments.StatsChampionshipFragment;
import ru.kpfu.itis.android.fragments.TableChampionshipFragment;
import ru.kpfu.itis.android.models.Championship;
import ru.kpfu.itis.android.models.modelForList.ChampionshipInList;

public class ChampionshipActivity extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private ImageView imgToolbar;
    private TextView tvNameChampionship;
    private Toolbar toolbar;
    private Championship detailChampionship;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ProgressBar progressBar;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championships);
        bind();
        ChampionshipInList championship = (ChampionshipInList) getIntent().getSerializableExtra("CHAMPIONSHIP");
//        appBarLayout.setBackgroundResource(R.drawable.uefa);
        TextView text = new TextView(this);
        text.setTextAppearance(this, android.R.style.TextAppearance_Material_Widget_ActionBar_Title_Inverse);
        text.setText("Турнир");
        toolbar.addView(text);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Glide.with(ChampionshipActivity.this)
                .load(R.drawable.uefa)
                .apply(RequestOptions.fitCenterTransform())
                .into(imgToolbar);
        tvNameChampionship.setText(championship.getName());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        setVisibleProgressBar(View.VISIBLE);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        requests.getChampionship(championship.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    if (response.code() == 200) {
                        detailChampionship = response.body();
                    } else if (response.code() == 400) {
                        Log.d("get chmp", "CODE " + response.code());
                        Toast.makeText(this, "Не удалось загрузить", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("get chmp", "Code " + response.code());
                    }
                    setVisibleProgressBar(View.GONE);
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("Get chmp", "THROW " + throwable.getMessage());
                    Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void bind() {
        appBarLayout = findViewById(R.id.appbar);
        imgToolbar = findViewById(R.id.imgToolbar);
        tvNameChampionship = findViewById(R.id.tv_toolbar_name_championship);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.pb_championships);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_championships, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private int COUNT_ITEMS = 3;
        private String tabTitles[] = new String[]{"Лента", "Таблица", "Статистика"};


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return NewsChampionshipFragment.newInstance(ChampionshipActivity.this);
                case 1:
                    return TableChampionshipFragment.newInstance(ChampionshipActivity.this, detailChampionship.getItems());
                case 2:
                    return StatsChampionshipFragment.newInstansce(ChampionshipActivity.this);
                default:
                    return NewsChampionshipFragment.newInstance(ChampionshipActivity.this);
            }
        }

        @Override
        public int getCount() {
            return COUNT_ITEMS;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
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

    public void setVisibleProgressBar(int visibility) {
        switch (visibility) {
            case View.VISIBLE:
                progressBar.setVisibility(visibility);
                mViewPager.setVisibility(View.GONE);
                break;
            case View.GONE:
                progressBar.setVisibility(visibility);
                mViewPager.setVisibility(View.VISIBLE);
                break;
        }
    }
}