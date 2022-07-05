package com.hyzertags;

public class Player {
    String firstName;
    String lastName;
    Integer tagNum;

    public Player () {
        this.firstName = "com.hyzertags.Player Name";
        this.lastName = "";
    }
    public Player (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        // TODO tag number generator?
    }
    public Player (String firstName, String lastName, Integer tagNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tagNum = tagNum;
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

    public Integer getTagNum() {
        return tagNum;
    }

    public void setTagNum(Integer tagNum) {
        this.tagNum = tagNum;
    }
}
