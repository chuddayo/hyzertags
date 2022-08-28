package com.hyzertags.controllers;

import com.hyzertags.repositories.EventResultsRepository;
import com.hyzertags.services.EventResultsService;
import com.hyzertags.services.dto.FinalResultDTO;
import com.hyzertags.services.dto.PartialResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hyzertags.domains.EventResults;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EventResultsController {
    private final EventResultsRepository eventResultsRepository;
    private final EventResultsService eventResultsService;

    public EventResultsController(EventResultsRepository eventResultsRepository, EventResultsService eventResultsService) {
        this.eventResultsRepository = eventResultsRepository;
        this.eventResultsService = eventResultsService;
    }

    /**
     * {@code POST  /event-results} : Create a new eventResults.
     */
    @PostMapping("/event-results")
    public ResponseEntity<EventResults> createEventResults(@RequestBody EventResults eventResults) {
        return new ResponseEntity<>(eventResultsRepository.save(eventResults), HttpStatus.CREATED);
    }

    /**
     * POST : calls the service to sort and finalize these results and creates new eventResults
     */
    @PostMapping("/event-results/list")
    public ResponseEntity<List<FinalResultDTO>> finalizeResults(@RequestBody List<PartialResultDTO> partialResults) {
        return new ResponseEntity<>(eventResultsService.postEventResults(partialResults), HttpStatus.CREATED);
    }

    /**
     * {@code PUT  /event-results/:id} : Updates an existing eventResults.
     */
    @PutMapping("/event-results/{id}")
    public ResponseEntity<EventResults> updateEventResults(
            @PathVariable Long id,
            @RequestBody EventResults eventResults) {
        eventResults.setId(id);
        return new ResponseEntity<>(eventResultsRepository.save(eventResults), HttpStatus.OK);
    }

    /**
     * {@code GET  /event-results} : get all the eventResults.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventResults in body.
     */
    @GetMapping("/event-results")
    public ResponseEntity<List<EventResults>> getAllEventResults() {
        return new ResponseEntity<>(eventResultsRepository.findAll(), HttpStatus.OK);
    }

    /**
     * {@code GET  /event-results/:id} : get the "id" eventResults.
     */
    @GetMapping("/event-results/{id}")
    public ResponseEntity<EventResults> getEventResults(@PathVariable Long id) {
        return new ResponseEntity<>(eventResultsRepository.findById(id).orElseGet(EventResults::new), HttpStatus.OK);
    }

    /**
     * {@code GET /event-results/league/:leagueID} : get the eventResults list by league "id"
     */
    @GetMapping("/event-results/league/{leagueID}")
    public ResponseEntity<List<EventResults>> getEventResultsByLeagueID(@PathVariable Long leagueID) {
        return new ResponseEntity<>(eventResultsRepository.findByLeagueID(leagueID), HttpStatus.OK);
    }

    // GET event results by eventID
    @GetMapping("/event-results/event/{eventID}")
    public ResponseEntity<List<EventResults>> getEventResultsByEventID(@PathVariable Long eventID) {
        return new ResponseEntity<>(eventResultsRepository.findByEventID(eventID), HttpStatus.OK);
    }

    // GET event results by playerID
    @GetMapping("/event-results/player/{playerID}")
    public ResponseEntity<List<EventResults>> getEventResultsByPlayerID(@PathVariable Long playerID) {
        return new ResponseEntity<>(eventResultsRepository.findByPlayerID(playerID), HttpStatus.OK);
    }

    /**
     * {@code DELETE  /event-results/:id} : delete the "id" eventResults.
     */
    @DeleteMapping("/event-results/{id}")
    public ResponseEntity<Boolean> deleteEventResults(@PathVariable Long id) {
        eventResultsRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
