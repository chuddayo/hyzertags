package com.hyzertags.domains;

import javax.persistence.*;

@Entity
@Table(name = "event_results")
public class EventResults implements Comparable<EventResults> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "league_id")
    private Long leagueID;

    @Column(name = "event_id")
    private Long eventID;

    @Column(name = "player_id")
    private Long playerID;

    @Column(name = "total_strokes_to_par")
    private Integer totalStrokesToPar;

    @Column(name = "incoming_tag")
    private Integer incomingTag;

    @Column(name = "outgoing_tag")
    private Integer outgoingTag;

    public EventResults() {
    }

    public EventResults(Long leagueID, Long eventID, Long playerID, Integer totalStrokesToPar, Integer incomingTag, Integer outgoingTag) {
        this.leagueID = leagueID;
        this.eventID = eventID;
        this.playerID = playerID;
        this.totalStrokesToPar = totalStrokesToPar;
        this.incomingTag = incomingTag;
        this.outgoingTag = outgoingTag;
    }

    public EventResults(Long id, Long leagueID, Long eventID, Long playerID, Integer totalStrokesToPar, Integer incomingTag, Integer outgoingTag) {
        this.id = id;
        this.leagueID = leagueID;
        this.eventID = eventID;
        this.playerID = playerID;
        this.totalStrokesToPar = totalStrokesToPar;
        this.incomingTag = incomingTag;
        this.outgoingTag = outgoingTag;
    }

    public Long getId() {
        return this.id;
    }

    public EventResults id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeagueID() {
        return this.leagueID;
    }

    public EventResults leagueID(Long leagueID) {
        this.setLeagueID(leagueID);
        return this;
    }

    public void setLeagueID(Long leagueID) {
        this.leagueID = leagueID;
    }

    public Long getEventID() {
        return this.eventID;
    }

    public EventResults eventID(Long eventID) {
        this.setEventID(eventID);
        return this;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getPlayerID() {
        return this.playerID;
    }

    public EventResults playerID(Long playerID) {
        this.setPlayerID(playerID);
        return this;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public Integer getTotalStrokesToPar() {
        return this.totalStrokesToPar;
    }

    public EventResults totalStrokesToPar(Integer totalStrokesToPar) {
        this.setTotalStrokesToPar(totalStrokesToPar);
        return this;
    }

    public void setTotalStrokesToPar(Integer totalStrokesToPar) {
        this.totalStrokesToPar = totalStrokesToPar;
    }

    public Integer getIncomingTag() {
        return this.incomingTag;
    }

    public EventResults incomingTag(Integer incomingTag) {
        this.setIncomingTag(incomingTag);
        return this;
    }

    public void setIncomingTag(Integer incomingTag) {
        this.incomingTag = incomingTag;
    }

    public Integer getOutgoingTag() {
        return this.outgoingTag;
    }

    public EventResults outgoingTag(Integer outgoingTag) {
        this.setOutgoingTag(outgoingTag);
        return this;
    }

    public void setOutgoingTag(Integer outgoingTag) {
        this.outgoingTag = outgoingTag;
    }

    @Override
    public int compareTo(EventResults o) {
        int eventNumDiff = o.eventID.compareTo(this.eventID);
        return eventNumDiff == 0 ? this.incomingTag.compareTo(o.incomingTag) : eventNumDiff;
    }
}