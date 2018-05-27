package ru.kpfu.itis.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.activities.NewsActivity;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.News;

/**
 * Created by hlopu on 08.04.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<News> mNewsList;

    public NewsAdapter(Context context) {
        this.context = context;
        mNewsList = Collections.emptyList();
    }

    public void setmNewsList(List<News> mNewsList) {
        this.mNewsList = mNewsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final News news = mNewsList.get(position);
        System.out.println("News Image"+news.getImage());
        Glide.with(context)
                .load(SportApiRequests.DOWNLOAD_IMAGE + news.getImage())
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.imgNews);
        holder.tvTitleNews.setText(news.getTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsActivity.class);
            intent.putExtra("ID_NEWS", news.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgNews;
        private TextView tvTitleNews;

        public NewsViewHolder(View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.iv_image_news);
            tvTitleNews = itemView.findViewById(R.id.title_news);

        }
    }
}
