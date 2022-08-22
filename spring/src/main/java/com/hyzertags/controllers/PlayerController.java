package com.hyzertags.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hyzertags.domains.Player;
import com.hyzertags.repositories.PlayerRepository;
import com.hyzertags.services.PlayerService;
import com.hyzertags.services.dto.LeagueStandingDTO;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    public PlayerController(PlayerRepository playerRepository, PlayerService playerService) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    /**
     * {@code POST  /players} : Create a new player.
     */
    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerRepository.save(player), HttpStatus.CREATED);
    }

    /**
     * {@code PUT  /players/:playerID} : Updates an existing player.
     */
    @PutMapping("/players/{playerID}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long playerID, @RequestBody Player player) {
        // TODO put logic
        return new ResponseEntity<>(playerRepository.save(player), HttpStatus.CREATED);
    }

    /**
     * {@code GET  /players} : get all the players.
     */
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    /**
     * {@code GET  /players/:playerID} : get the "id" player.
     */
    @GetMapping("/players/{playerID}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long playerID) {
        return new ResponseEntity<>(playerRepository.findById(playerID).orElseGet(Player::new), HttpStatus.OK);
    }

    // GET player tag # by playerid and leagueid
    @GetMapping("/players/{playerID}/leaguetag/{leagueID}")
    public ResponseEntity<Integer> getPlayerTagByLeagueID(@PathVariable Long playerID, @PathVariable Long leagueID) {
        return new ResponseEntity<>(playerService.getPlayerTagByLeagueID(playerID, leagueID), HttpStatus.OK);
    }

    // GET all tag #s a player holds by playerID
    @GetMapping("/players/{playerID}/alltags")
    public ResponseEntity<List<LeagueStandingDTO>> getAllLeagueStandingsByPlayer(@PathVariable Long playerID) {
        return new ResponseEntity<>(playerService.getAllLeagueStandingsByPlayerID(playerID), HttpStatus.OK);
    }

    /**
     * {@code DELETE  /players/:playerID} : delete the "id" player.
     */
    @DeleteMapping("/players/{playerID}")
    public ResponseEntity<Boolean> deletePlayer(@PathVariable Long playerID) {
        playerRepository.deleteById(playerID);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
