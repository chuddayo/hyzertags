import React, { useEffect, useState } from "react";
import "./LeagueManager.css";
import requests from "../requests";
import axios from "../axios";
import EventTable from "./EventTable";
import EventResultsTable from "./EventResultsTable";

function Standings() {
  // LeagueManager
  // initial component GET request for leagues list
  const [leagues, setLeagues] = useState([]);

  // keeps track of value of selected league in dropdown
  const [leagueValue, setLeagueValue] = useState("");

  // gets league standings upon selection
  const [leagueSelect, setLeagueSelect] = useState([]);

  // triggers event table component and hides standings table
  const [showEventTable, setShowEventTable] = useState(false);

  // updated on each player check-in
  const [playersCheckedIn, setPlayersCheckedIn] = useState(leagueSelect);

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

  // called when league is selected from dropdown
  const leagueChange = (e) => {
    setLeagueValue(e.target.value);

    // need to inject
    axios
      .get(`${requests.fetchLeagues}/${e.target.value}/standings`)
      .then((response) => {
        response.data.forEach((playerStanding, index) => {
          response.data[index] = {
            ...playerStanding,
            selected: false,
            strokesToPar: null,
            leagueID: parseInt(e.target.value),
          };
        });
        setLeagueSelect(response.data);
      });
  };

  // called when a check-box is selected for event check-in
  const playerCheckIn = (item) => {
    leagueSelect.map((playerStanding) => {
      if (playerStanding.player.id === item.player.id) {
        playerStanding.selected
          ? (playerStanding.selected = false)
          : (playerStanding.selected = true);
      }
      return playerStanding;
    });
    setPlayersCheckedIn(leagueSelect.filter((e) => e.selected));
  };

  // called when Create Event button is clicked
  const eventTriggerClick = () => {
    setShowEventTable(!showEventTable);
  };

  const handleEventResults = (results) => {
    setEventResults(results);
  }

  return (
    <div>
      {!showEventTable && <h4>select league to see standings</h4>}
      {/* Dropdown Select */}
      {!showEventTable && (
        <div className="custom-select">
          <select value={leagueValue} onChange={leagueChange}>
            <option key="0">-- Select League --</option>
            {leagues.map((league) => (
              <option key={league.id} value={league.id}>
                {league.name}
              </option>
            ))}
          </select>
        </div>
      )}

      {/* Standings Table */}
      {!showEventTable && leagueValue && (
        <table className="styled-table">
          <thead>
            <tr>
              <th>Tag #</th>
              <th>Name</th>
              <th>Check-In</th>
            </tr>
          </thead>
          <tbody>
            {leagueSelect.map((playerRow) => (
              <tr key={playerRow.player.id}>
                <td>{playerRow.playerTag}</td>
                <td>
                  {playerRow.player.firstName} {playerRow.player.lastName}
                </td>
                <td>
                  <input
                    type="checkbox"
                    id={playerRow.player.id}
                    onChange={(e) => {
                      playerCheckIn(playerRow);
                    }}
                  />
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {/* Create Event Button */}
      {leagueValue && !showEventTable && <button onClick={eventTriggerClick}>Create Event</button>}

      {showEventTable && eventResults.length === 0 && <EventTable eventResults={handleEventResults} playerList={playersCheckedIn} />}
      {eventResults.length > 0 && <EventResultsTable eventResults={eventResults}/>}
    </div>
  );
}

export default Standings;
