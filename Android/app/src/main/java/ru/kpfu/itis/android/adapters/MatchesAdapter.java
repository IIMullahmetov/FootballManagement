package ru.kpfu.itis.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.Collections;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.activities.MatchActivity;
import ru.kpfu.itis.android.models.Championship;
import ru.kpfu.itis.android.models.ChampionshipTitle;
import ru.kpfu.itis.android.models.Match;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by hlopu on 08.04.2018.
 */

public class MatchesAdapter extends ExpandableRecyclerViewAdapter<MatchesAdapter.TitleViewHolder, MatchesAdapter.MatchesViewHolder> {
    private Context context;
    private List<ChampionshipTitle> matchList;
    private MatchListener matchListener;

    public MatchesAdapter(Context context, List<ChampionshipTitle> groups) {
        super(groups);
        this.context = context;
        matchList = groups;
    }


    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_championship, parent, false);
        return new TitleViewHolder(view);
    }

    @Override
    public MatchesViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_item, parent, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(MatchesViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Match match = ((ChampionshipTitle) group).getItems().get(childIndex);
        holder.tvNameTeam1.setText(match.getTeam1());
        holder.tvNameTeam2.setText(match.getTeam2());
        holder.tvDate.setText(match.getDate());

        holder.itemView.setOnClickListener(view -> {
            if(matchListener!=null){
                matchListener.onMatchClick(match);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setChampionshipTitle(group);
    }

    public void setMatchListener(MatchListener matchListener) {
        this.matchListener = matchListener;
    }

    public class MatchesViewHolder extends ChildViewHolder {
        private TextView tvDate;
        private TextView tvNameTeam1;
        private TextView tvNameTeam2;


        public MatchesViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.date_championships);
            tvNameTeam1 = itemView.findViewById(R.id.match_team1);
            tvNameTeam2 = itemView.findViewById(R.id.match_team2);

        }
    }

    public class TitleViewHolder extends GroupViewHolder {

    private TextView championshipTitle;
    private ImageView arrow;

    public TitleViewHolder(View itemView) {
        super(itemView);
        championshipTitle = itemView.findViewById(R.id.title);
        arrow = itemView.findViewById(R.id.list_item_genre_arrow);
    }

    public void setChampionshipTitle(ExpandableGroup group) {
        championshipTitle.setText(group.getTitle());
    }

        @Override
        public void expand() {
            animateExpand();
        }

        @Override
        public void collapse() {
            animateCollapse();
        }

        private void animateExpand() {
            RotateAnimation rotate =
                    new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            arrow.setAnimation(rotate);
        }

        private void animateCollapse() {
            RotateAnimation rotate =
                    new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            arrow.setAnimation(rotate);
        }

}

    public interface MatchListener{
        void onMatchClick(Match match);
    }
}
