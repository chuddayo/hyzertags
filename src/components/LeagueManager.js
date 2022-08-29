import React, { useEffect, useState } from "react";
import "./LeagueManager.css";
import requests from "../requests";
import axios from "../axios";
import EventTable from "./EventTable";
import EventResultsTable from "./EventResultsTable";
import LeagueSelection from "./LeagueSelection";

function Standings() {
  // initial component GET request for leagues list
  const [leagues, setLeagues] = useState([]);

  // gets league standings upon selection
  const [leagueStandings, setLeagueStandings] = useState([]);

  // triggers event table component and hides standings table
  const [showEventTable, setShowEventTable] = useState(false);

  // updated on each player check-in
  const [playersCheckedIn, setPlayersCheckedIn] = useState(leagueStandings);

  // called back from child to show full event results table
  const [eventResults, setEventResults] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const request = await axios.get(`${requests.fetchLeagues}`);
      setLeagues(request.data);
      return request.data;
    }
    fetchData();
  }, []);

  // called when a check-box is selected for event check-in
  const playerCheckIn = (item) => {
    leagueStandings.map((playerStanding) => {
      if (playerStanding.player.id === item.player.id) {
        playerStanding.selected
          ? (playerStanding.selected = false)
          : (playerStanding.selected = true);
      }
      return playerStanding;
    });
    setPlayersCheckedIn(leagueStandings.filter((e) => e.selected));
  };

  // called when Create Event button is clicked
  const eventTriggerClick = () => {
    setShowEventTable(!showEventTable);
  };

  const handleEventResults = (results) => {
    setEventResults(results);
  };

  const handleLeagueStandingsChange = (standings) => {
    setLeagueStandings(standings);
  };

  return (
    <div>
      {/* league selection */}
      {!showEventTable && (
        <LeagueSelection
          leagues={leagues}
          leagueStandings={leagueStandings}
          playerCheckIn={playerCheckIn}
          handleLeagueStandingsChange={handleLeagueStandingsChange}
          eventTriggerClick={eventTriggerClick}
        />
      )}

      {/* Event Table to Enter Results */}
      {showEventTable && eventResults.length === 0 && (
        <EventTable
          eventResults={handleEventResults}
          playerList={playersCheckedIn}
        />
      )}

      {/* Final Results Sorted By Outgoing Tag */}
      {eventResults.length > 0 && (
        <EventResultsTable eventResults={eventResults} />
      )}
    </div>
  );
}

export default Standings;
