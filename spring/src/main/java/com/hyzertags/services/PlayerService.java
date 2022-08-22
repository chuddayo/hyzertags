package com.hyzertags.services;

import com.hyzertags.domains.EventResults;
import com.hyzertags.domains.League;
import com.hyzertags.domains.Player;
import com.hyzertags.repositories.EventResultsRepository;
import com.hyzertags.repositories.LeagueRepository;
import com.hyzertags.repositories.PlayerRepository;
import com.hyzertags.services.dto.LeagueStandingDTO;
import com.hyzertags.services.dto.PlayerStandingDTO;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    LeagueRepository leagueRepository;
    @Autowired
    EventResultsRepository eventResultsRepository;

    public PlayerService(
            PlayerRepository playerRepository,
            LeagueRepository leagueRepository,
            EventResultsRepository eventResultsRepository
    ) {
        this.playerRepository = playerRepository;
        this.leagueRepository = leagueRepository;
        this.eventResultsRepository = eventResultsRepository;
    }

    public Set<Player> getAllPlayersInLeague(Long leagueID) {
        List<EventResults> eventResults = eventResultsRepository.findByLeagueID(leagueID);
        Set<Player> playerSet = new HashSet<>();
        for (EventResults e : eventResults) {
            playerSet.add(playerRepository.findById(e.getPlayerID()).orElseGet(Player::new));
        }
        return playerSet;
    }

    public List<EventResults> getAllPlayerResults(Long playerID) {
        return eventResultsRepository.findByPlayerID(playerID);
    }

    public Integer getPlayerTagByLeagueID(Long playerID, Long leagueID) {
        List<EventResults> playerResults = eventResultsRepository.findByPlayerID(playerID);
        Collections.sort(playerResults); // sorts most recent event first
        for (EventResults e : playerResults) {
            if (e.getLeagueID().equals(leagueID)) {
                if (e.getOutgoingTag() != null) return e.getOutgoingTag();
            }
        }
        return -1;
    }

    public List<PlayerStandingDTO> getLeagueStandings(Long leagueID) {
        List<EventResults> leagueResults = eventResultsRepository.findByLeagueID(leagueID);
        Collections.sort(leagueResults);
        List<Long> playersTracked = new ArrayList<>();
        List<PlayerStandingDTO> leagueStandings = new ArrayList<>();

        for (EventResults e : leagueResults) {
            if (!playersTracked.contains(e.getPlayerID())) {
                leagueStandings.add(
                        new PlayerStandingDTO(e.getOutgoingTag(), playerRepository.findById(e.getPlayerID()).orElseGet(Player::new))
                );
                playersTracked.add(e.getPlayerID());
            }
        }

        Collections.sort(leagueStandings);
        return leagueStandings;
    }

    public List<LeagueStandingDTO> getAllLeagueStandingsByPlayerID(Long playerID) {
        List<LeagueStandingDTO> leagueStandings = new ArrayList<>();
        List<League> leagueList = leagueRepository.findAll();

        for (League l : leagueList) {
            int tagResult = getPlayerTagByLeagueID(playerID, l.getId());
            if (tagResult > 0) leagueStandings.add(new LeagueStandingDTO(tagResult, l));
        }

        return leagueStandings;
    }
}
