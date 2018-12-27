package com.soccerleague;

public class Team {
    private String mStanding;
    private String mName;

    public Team(String mStanding, String mName) {
        this.mStanding = mStanding;
        this.mName = mName;
    }

    public String getStanding() {
        return mStanding;
    }

    public String getName() {
        return mName;
    }


}
