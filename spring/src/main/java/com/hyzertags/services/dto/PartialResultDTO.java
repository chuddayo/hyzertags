package com.hyzertags.services.dto;

// an object that will be sorted by eventID to determine outgoing tag before
// being decorated into a full EventResults object and posted to DB
public class PartialResultDTO implements Comparable<PartialResultDTO> {
    private Long leagueID;
    private Long eventID;
    private Long playerID;
    private Integer totalStrokesToPar;
    private Integer incomingTag;

    public PartialResultDTO() {
    }

    public PartialResultDTO(Long leagueID, Long eventID, Long playerID, Integer totalStrokesToPar, Integer incomingTag) {
        this.leagueID = leagueID;
        this.eventID = eventID;
        this.playerID = playerID;
        this.totalStrokesToPar = totalStrokesToPar;
        this.incomingTag = incomingTag;
    }

    public Long getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(Long leagueID) {
        this.leagueID = leagueID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public Integer getTotalStrokesToPar() {
        return totalStrokesToPar;
    }

    public void setTotalStrokesToPar(Integer totalStrokesToPar) {
        this.totalStrokesToPar = totalStrokesToPar;
    }

    public Integer getIncomingTag() {
        return incomingTag;
    }

    public void setIncomingTag(Integer incomingTag) {
        this.incomingTag = incomingTag;
    }

    @Override
    public int compareTo(PartialResultDTO o) {
        int strokesDiff = this.totalStrokesToPar.compareTo(o.totalStrokesToPar);
        return strokesDiff == 0 ? this.incomingTag.compareTo(o.incomingTag) : strokesDiff;
    }
}
