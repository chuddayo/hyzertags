package com.hyzertags.repositories;

import com.hyzertags.domains.League;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {}
