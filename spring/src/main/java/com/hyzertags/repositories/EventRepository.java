package com.hyzertags.repositories;

import com.hyzertags.domains.Event;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}
