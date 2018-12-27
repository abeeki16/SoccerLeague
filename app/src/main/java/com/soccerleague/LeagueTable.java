package com.soccerleague;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        parseJSON();

    }

    private void parseJSON() {
        String url = "http://api.football-data.org/v2/competitions/";
        url += "PL";
        url += "/standings";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray standings = response.getJSONArray("standings");
                            JSONObject elem = standings.getJSONObject(0);
                            JSONArray table = elem.getJSONArray("table");
                            for (int i=0;i<table.length();i++) {
                                JSONObject tableObject = table.getJSONObject(i);
                                JSONObject team = tableObject.getJSONObject("team");

                                String position = tableObject.getString("position");
                                String teamName = team.getString("name");
                                Log.i("\nteam Name ",teamName);


                                mTeamList.add(new Team(position,teamName));

                            }

                            mTableAdapter = new TableAdapter(LeagueTable.this,mTeamList);
                            mRecyclerView.setAdapter(mTableAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("X-Auth-Token", "3ee2c2f395e24196bfec72d713209910");
                return headers;
            }
        };

        mRequestQueue.add(request);

    }

}
