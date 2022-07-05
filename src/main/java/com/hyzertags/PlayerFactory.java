package com.hyzertags;
// TODO import faker library to create random players?
public class PlayerFactory {
    public static Player createPlayer(String firstName, String lastName) {
        Integer tagNum = League.getNumberOfPlayers() + 1;
        return new Player(firstName, lastName, tagNum);
    }
}
