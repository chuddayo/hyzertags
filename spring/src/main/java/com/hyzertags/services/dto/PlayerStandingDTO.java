package com.hyzertags.services.dto;

import com.hyzertags.domains.Player;

public class PlayerStandingDTO implements Comparable<PlayerStandingDTO> {

    private Integer playerTag;
    private Player player;

    public PlayerStandingDTO() {}

    public PlayerStandingDTO(Integer playerTag, Player player) {
        this.playerTag = playerTag;
        this.player = player;
    }

    public Integer getPlayerTag() {
        return playerTag;
    }

    public void setPlayerTag(Integer playerTag) {
        this.playerTag = playerTag;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int compareTo(PlayerStandingDTO o) {
        int tagDiff = this.playerTag.compareTo(o.playerTag);
        return tagDiff == 0 ? this.player.getLastName().compareTo(o.player.getLastName()) : tagDiff;
    }
}
