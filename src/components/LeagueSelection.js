import React, { useState, useEffect } from "react";
import axios from "../axios";
import requests from "../requests";
import "./LeagueSelection.css";

function LeagueSelection({
  leagues,
  leagueStandings,
  playerCheckIn,
  handleLeagueStandingsChange,
  eventTriggerClick,
}) {
  // keeps track of value of selected league in dropdown
  const [selectedLeague, setSelectedLeague] = useState("");
  const [showAddPlayerInput, setShowAddPlayerInput] = useState(false);
  const [nextTag, setNextTag] = useState(0);

  useEffect(() => {
    if(selectedLeague !== "") {
      async function fetchNextTag() {
        const request = await axios.get(
          `${requests.fetchLeagues}/${selectedLeague}/nexttag`
        );
        setNextTag(request.data);
        return request.data;
      }
      fetchNextTag();
    }
  }, [selectedLeague]);

  // called when league is selected from dropdown
  const leagueChange = (e) => {
    setSelectedLeague(e.target.value);
    console.log(e.target.value);

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
        handleLeagueStandingsChange(response.data);
      });
  };

  const handlePlayerAddition = () => {
    setShowAddPlayerInput(!showAddPlayerInput);
    console.log(leagueStandings);
    console.log(nextTag);
    // setNextTag(nextTag + 1);
  };


  return (
    <div>
      {/* Dropdown Select */}
      {!showAddPlayerInput && <><h4>select league to see standings</h4>
      <div className="custom-select">
        <select value={selectedLeague} onChange={leagueChange}>
          <option key="0">-- Select League --</option>
          {leagues.map((league) => (
            <option key={league.id} value={league.id}>
              {league.name}
            </option>
          ))}
        </select>
      </div></>}

      {/* Standings Table */}
      {selectedLeague && (
        <table className="styled-table">
          <thead>
            <tr>
              <th>Tag #</th>
              <th>Name</th>
              <th>Check-In</th>
            </tr>
          </thead>
          <tbody>
            {leagueStandings.map((playerRow) => (
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

      {selectedLeague && <>
        {showAddPlayerInput && <input
          placeholder="First Last"
          className="text-input"
          // onChange={(e) => {
          //   handleEventNameChange(e);
          // }}
        />}
        <div className="flex-buttons">
          <button onClick={handlePlayerAddition}>Add Player</button>
          <button onClick={eventTriggerClick}>Create Event</button>
        </div>
      </>}
    </div>
  );
}

export default LeagueSelection;
