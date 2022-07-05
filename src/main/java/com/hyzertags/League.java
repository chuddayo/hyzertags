package com.hyzertags;

import java.util.ArrayList;

public class League {
    private static ArrayList<Player> leagueList = new ArrayList<>();

    public static ArrayList<Player> getLeagueList() {
        return leagueList;
    }
    public static void setLeagueList(ArrayList<Player> leagueList) {
        League.leagueList = leagueList;
    }
    public static Integer getNumberOfPlayers() {
        return leagueList.size();
    }
}
