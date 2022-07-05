package com.hyzertags;

public class PlayerFactory {
    public static Player createPlayer(String firstName, String lastName) {
        Integer tagNum = League.getNumberOfPlayers() + 1;
        return new Player(firstName, lastName, tagNum);
    }
}
