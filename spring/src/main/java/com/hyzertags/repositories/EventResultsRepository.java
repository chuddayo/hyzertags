package com.hyzertags.repositories;

import com.hyzertags.domains.EventResults;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventResultsRepository extends JpaRepository<EventResults, Long> {
    List<EventResults> findByLeagueID(Long leagueID);
    List<EventResults> findByPlayerID(Long playerID);
    List<EventResults> findByEventID(Long eventID);
}