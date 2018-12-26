package com.soccerleague;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TeamViewHolder> {

    private ArrayList<Team> mTeamList;
    private Context mContext;

    public TableAdapter(Context context, ArrayList<Team> teamList) {
        mTeamList = teamList;
        mContext = context;
    }


    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_card,parent,false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team currentTeam = mTeamList.get(position);
        holder.mTextViewTeam.setText(currentTeam.getName());
        holder.mTextViewRanking.setText(currentTeam.getStanding());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewRanking;
        public TextView mTextViewTeam;
        public TeamViewHolder(View itemView) {
            super(itemView);
            mTextViewRanking = itemView.findViewById(R.id.ranking);
            mTextViewTeam = itemView.findViewById(R.id.team);
        }
    }
}
