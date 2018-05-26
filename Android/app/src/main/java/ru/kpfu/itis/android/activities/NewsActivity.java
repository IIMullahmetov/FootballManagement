package ru.kpfu.itis.android.activities;

import android.annotation.SuppressLint;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.adapters.CommentsAdapter;
import ru.kpfu.itis.android.adapters.NewsAdapter;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.news.CommentPOST;
import ru.kpfu.itis.android.models.news.ItemNews;
import ru.kpfu.itis.android.models.news.NewsDetail;
import ru.kpfu.itis.android.providers.SharedPreferencesProvider;

public class NewsActivity extends AppCompatActivity {
    private TextView tvTitle;
    private ProgressBar progressBar;
    private LinearLayout llContent;
    private RecyclerView rvComments;
    private CommentsAdapter commentAdapter;
    private Button btnSend;
    private EditText etComment;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        bind();
        setVisibleProgressBar(View.VISIBLE);

        int idNews = getIntent().getIntExtra("ID_NEWS", 0);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        requests.getPost(idNews).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.code() == 200) {
                        Log.d("News ID", String.valueOf(response.body().getId()));
                        fillFields(response.body());
                        Log.d("News Title", response.body().getTitle());
                        setVisibleProgressBar(View.GONE);
                    } else {
                        setVisibleProgressBar(View.GONE);
                        Log.d("Get News", "THROW " + response.message());
                    }
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("Get News", "THROW " + throwable.getMessage());
                    Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentsAdapter(this);
        rvComments.setAdapter(commentAdapter);

        requests.getComments(idNews).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.code() == 200) {
                        Log.d("Comment size", String.valueOf(response.body().getItems().size()));
                        commentAdapter.setComments(response.body().getItems());
                        commentAdapter.notifyDataSetChanged();
                    } else {
                        Log.d("Get comments", "THROW " + response.message());
                    }
                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("Get comments", "THROW " + throwable.getMessage());
                    Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
        btnSend.setOnClickListener(v -> {
            if(etComment.length()==0) Toast.makeText(this, "Введите текст комментария", Toast.LENGTH_SHORT).show();
            else{
                btnSend.setVisibility(View.GONE);
                Log.d("Token", SharedPreferencesProvider.getInstance(this).getUserTokken());
                requests.addComments(idNews, new CommentPOST(etComment.getText().toString()),
                        "Bearer "+ SharedPreferencesProvider.getInstance(this).getUserTokken())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            if (response.code() == 200) {
                                Log.d("Comment add", String.valueOf(response.body().getId()));
                                etComment.setText("");
                                commentAdapter.addComment(response.body());
                                commentAdapter.notifyDataSetChanged();
                                Toast.makeText(this, "Добавление комментария успешно", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("Comment add", "THROW " + response.message()+" "+response.code());
                            }
                            btnSend.setVisibility(View.VISIBLE);
                        }, throwable -> {
                            btnSend.setVisibility(View.VISIBLE);
                            setVisibleProgressBar(View.GONE);
                            Log.d("comment add", "THROW " + throwable.getMessage());
                            Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }

    public void bind() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Новость");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        llContent = findViewById(R.id.ll_content_news);
        tvTitle = findViewById(R.id.tv_intro);
        progressBar = findViewById(R.id.pb_news);
        rvComments = findViewById(R.id.rv_comments);
        btnSend = findViewById(R.id.btn_send_review);
        etComment = findViewById(R.id.et_review);
    }

    public void fillFields(NewsDetail newsDetail) {
        tvTitle.setText(newsDetail.getTitle());

        for (ItemNews itemNews : newsDetail.getItems()) {
            switch (itemNews.getType()) {
                case "text":
                    TextView textView = new TextView(this);
                    textView.setText(itemNews.getText());
                    llContent.addView(textView);
                    break;
                case "image":
                    ImageView imageView = new ImageView(this);
                    llContent.addView(imageView);
                    Glide.with(this)
                            .load(SportApiRequests.DOWNLOAD_IMAGE + itemNews.getGuid())
                            .apply(RequestOptions.fitCenterTransform())
                            .into(imageView);
                    break;
            }
        }

    }

    public void setVisibleProgressBar(int visibility) {
        switch (visibility) {
            case View.VISIBLE:
                progressBar.setVisibility(visibility);
                etComment.setVisibility(View.GONE);
                btnSend.setVisibility(View.GONE);
                break;
            case View.GONE:
                progressBar.setVisibility(visibility);
                etComment.setVisibility(View.VISIBLE);
                btnSend.setVisibility(View.VISIBLE);
                break;
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
}
