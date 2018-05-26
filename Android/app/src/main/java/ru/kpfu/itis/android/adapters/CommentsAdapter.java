package ru.kpfu.itis.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.models.news.Comment;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    private List<Comment> comments;
    private Context context;

    public CommentsAdapter(Context context) {
        this.context = context;
        comments = Collections.emptyList();
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        final Comment comment = comments.get(position);
        holder.tvName.setText(comment.getFirstName());
        holder.tvTextComment.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCommentPhoto;
        TextView tvName;
        TextView tvTextComment;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            ivCommentPhoto = itemView.findViewById(R.id.iv_reviewer_photo);
            tvName = itemView.findViewById(R.id.tv_reviewer_name);
            tvTextComment = itemView.findViewById(R.id.tv_review);
        }
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
