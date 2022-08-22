package com.hyzertags.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hyzertags.domains.League;
import com.hyzertags.domains.Player;
import com.hyzertags.repositories.LeagueRepository;
import com.hyzertags.services.PlayerService;
import com.hyzertags.services.dto.PlayerStandingDTO;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LeagueController {

    private final LeagueRepository leagueRepository;
    private final PlayerService playerService;

    public LeagueController(LeagueRepository leagueRepository, PlayerService playerService) {
        this.leagueRepository = leagueRepository;
        this.playerService = playerService;
    }

    /**
     * {@code POST  /leagues} : Create a new league.
     */
    @PostMapping("/leagues")
    public ResponseEntity<League> createLeague(@RequestBody League league) {
        return new ResponseEntity<>(leagueRepository.save(league), HttpStatus.CREATED);
    }

    /**
     * {@code PUT  /leagues/:id} : Updates an existing league.
     */
    @PutMapping("/leagues/{leagueID}")
    public ResponseEntity<League> updateLeague(@PathVariable Long leagueID, @RequestBody League league) {
        // TODO PUT logic
        return new ResponseEntity<>(leagueRepository.save(league), HttpStatus.CREATED);
    }

    /**
     * {@code GET  /leagues} : get all the leagues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leagues in body.
     */
    @GetMapping("/leagues")
    public ResponseEntity<List<League>> getAllLeagues() {
        return new ResponseEntity<>(leagueRepository.findAll(), HttpStatus.OK);
    }

    /**
     * {@code GET  /leagues/:leagueID} : get the "id" league.
     */
    @GetMapping("/leagues/{leagueID}")
    public ResponseEntity<League> getLeague(@PathVariable Long leagueID) {
        return new ResponseEntity<>(leagueRepository.findById(leagueID).orElseGet(League::new), HttpStatus.OK);
    }

    // GET all players in a particular league by leagueID
    @GetMapping("/leagues/{leagueID}/players")
    public ResponseEntity<Set<Player>> getPlayersInLeagueByLeagueID(@PathVariable Long leagueID) {
        return new ResponseEntity<>(playerService.getAllPlayersInLeague(leagueID), HttpStatus.OK);
    }

    // GET next tag # to be assigned in a league by league ID
    @GetMapping("leagues/{leagueID}/nexttag")
    public ResponseEntity<Integer> getNextTagByLeagueID(@PathVariable Long leagueID) {
        return new ResponseEntity<>(playerService.getAllPlayersInLeague(leagueID).size() + 1, HttpStatus.OK);
    }

    /**
     * {@code GET /leagues/:leagueID/standings} : get a list of player standings sorted by lowest tag number
     * each standing is the tag number and the corresponding player object
     */
    @GetMapping("/leagues/{leagueID}/standings")
    public ResponseEntity<List<PlayerStandingDTO>> getLeagueStandings(@PathVariable Long leagueID) {
        return new ResponseEntity<>(playerService.getLeagueStandings(leagueID), HttpStatus.OK);
    }

    /**
     * {@code DELETE  /leagues/:leagueID} : delete the "id" league.
     */
    @DeleteMapping("/leagues/{leagueID}")
    public ResponseEntity<Boolean> deleteLeague(@PathVariable Long leagueID) {
        leagueRepository.deleteById(leagueID);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
