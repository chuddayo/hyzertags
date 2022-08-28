package com.hyzertags.services.dto;

// an object that will be sent back to populate the details desired
// to display full final event results
public class FinalResultDTO {
    private Long eventResultsID;
    private String eventName;
    private String firstName;
    private String lastName;
    private Integer totalStrokesToPar;
    private Integer incomingTag;
    private Integer outgoingTag;

    public FinalResultDTO() {
    }

    public FinalResultDTO(Long eventResultsID, String eventName, String firstName, String lastname, Integer totalStrokesToPar, Integer incomingTag, Integer outgoingTag) {
        this.eventResultsID = eventResultsID;
        this.eventName = eventName;
        this.firstName = firstName;
        this.lastName = lastname;
        this.totalStrokesToPar = totalStrokesToPar;
        this.incomingTag = incomingTag;
        this.outgoingTag = outgoingTag;
    }

    public Long getEventResultsID() {
        return eventResultsID;
    }

    public void setEventResultsID(Long eventResultsID) {
        this.eventResultsID = eventResultsID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Integer getOutgoingTag() {
        return outgoingTag;
    }

    public void setOutgoingTag(Integer outgoingTag) {
        this.outgoingTag = outgoingTag;
    }
}
