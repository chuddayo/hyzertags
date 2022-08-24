package com.hyzertags.services;

import com.hyzertags.domains.EventResults;
import com.hyzertags.repositories.EventResultsRepository;
import com.hyzertags.services.dto.PartialResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventResultsService {
    @Autowired
    EventResultsRepository eventResultsRepository;

    public EventResultsService (EventResultsRepository eventResultsRepository) {
        this.eventResultsRepository = eventResultsRepository;
    }

    public List<EventResults> postEventResults(List<PartialResultDTO> partialResults) {
        // gather tags in play and sort them
        List<Integer> tagsInPlay = new ArrayList<>();
        for (PartialResultDTO p : partialResults) {
            tagsInPlay.add(p.getIncomingTag());
        }
        Collections.sort(tagsInPlay);

        // partial results are sorted by lowest score to par
        // tie-breakers are broken by lowest tag coming in
        // event results are created mapping the sorted available tags
        Collections.sort(partialResults);
        List<EventResults> finalResults = new ArrayList<>();
        for (int i = 0; i < partialResults.size(); i++) {
            finalResults.add(
                    new EventResults(
                            partialResults.get(i).getLeagueID(),
                            partialResults.get(i).getEventID(),
                            partialResults.get(i).getPlayerID(),
                            partialResults.get(i).getTotalStrokesToPar(),
                            partialResults.get(i).getIncomingTag(),
                            tagsInPlay.get(i)));
        }

        // each event result is posted to the db
        for (EventResults e : finalResults) {
            eventResultsRepository.save(e);
        }

        return finalResults;
    }
}
