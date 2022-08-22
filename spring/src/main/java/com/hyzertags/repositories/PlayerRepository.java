package com.hyzertags.repositories;

import com.hyzertags.domains.Player;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {}
