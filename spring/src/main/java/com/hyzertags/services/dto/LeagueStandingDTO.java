package com.hyzertags.services.dto;

import com.hyzertags.domains.League;

// a list of LeagueStandingDTO will show a player's tag and league info for each league they're in
public class LeagueStandingDTO implements Comparable<LeagueStandingDTO> {

    private Integer playerTag;
    private League league;

    public LeagueStandingDTO() {}

    public LeagueStandingDTO(Integer playerTag, League league) {
        this.playerTag = playerTag;
        this.league = league;
    }

    public Integer getPlayerTag() {
        return playerTag;
    }

    public void setPlayerTag(Integer playerTag) {
        this.playerTag = playerTag;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public int compareTo(LeagueStandingDTO o) {
        return o.league.getId().compareTo(this.league.getId());
    }
}

