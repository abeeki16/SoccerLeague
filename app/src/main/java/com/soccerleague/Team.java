package com.soccerleague;

public class Team {
    private int mStanding;
    private String mName;

    public Team(int mStanding, String mName) {
        this.mStanding = mStanding;
        this.mName = mName;
    }

    public int getStanding() {
        return mStanding;
    }

    public String getName() {
        return mName;
    }


}
