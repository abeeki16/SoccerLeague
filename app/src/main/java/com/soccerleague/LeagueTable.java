package com.soccerleague;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class LeagueTable extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TableAdapter mTableAdapter;
    private ArrayList<Team> mTeamList;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTeamList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

    }

    private void parseJSON(String league) {
        String url = "http://api.football-data.org/v2/competitions/";
        url+=league;
        url+="/standings";

    }

}
