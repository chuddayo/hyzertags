import React, { useState, useEffect } from "react";
import axios from "../axios";
import requests from "../requests";
import "./LeagueSelection.css";
import LeagueCreation from "./LeagueCreation";

function LeagueSelection({
  leagues,
  leagueStandings,
  playerCheckIn,
  handleLeagueStandingsChange,
  eventTriggerClick,
}) {
  // keeps track of value of selected league in dropdown
  const [selectedLeague, setSelectedLeague] = useState("");
  // toggles add player text-input field
  const [showAddPlayerInput, setShowAddPlayerInput] = useState(false);
  // keeps track of value of new player name in text-input field
  const [newPlayerName, setNewPlayerName] = useState("");
  // keeps track of next tag to be assigned in league
  const [nextTag, setNextTag] = useState(0);

  const passNewlyCreatedLeague = (newLeague) => {
    setSelectedLeague(newLeague);
  };

  // initial component GET request for a league's next to be assigned tag number
  useEffect(() => {
    if (selectedLeague !== "") {
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
    console.log("selected league: ", e.target.value);

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

  const handleAddPlayerNameChange = (e) => {
    setNewPlayerName(e.target.value);
  };

  async function addPlayerToDB() {
    let newPlayer = await axios.post(`${requests.fetchPlayers}`, {
      firstName: newPlayerName.substring(0, newPlayerName.indexOf(" ")),
      lastName: newPlayerName.substring(newPlayerName.indexOf(" ") + 1)
    });
    return newPlayer;
  }

  const handlePlayerAddition = () => {
    setShowAddPlayerInput(!showAddPlayerInput);
    if (newPlayerName !== "") {
      addPlayerToDB().then((newPlayer) => {
        handleLeagueStandingsChange(
          leagueStandings.concat({
            leagueID: parseInt(selectedLeague),
            playerTag: nextTag,
            player: newPlayer.data,
            selected: false,
            strokesToPar: null,
          })
        );
        setNextTag(nextTag + 1);
        setNewPlayerName(""); 
      }).catch((error) => {
        console.log(error);
      });
    }
  };

  return (
    <div>
      {/* Dropdown Select */}
      {!showAddPlayerInput && !selectedLeague && (
        <>
          <h4>select league to see standings</h4>
          <div className="custom-select">
            <select value={selectedLeague} onChange={leagueChange}>
              <option key="0">-- Select League --</option>
              {leagues.map((league) => (
                <option key={league.id} value={league.id}>
                  {league.name}
                </option>
              ))}
            </select>
          </div>
          <LeagueCreation passNewlyCreatedLeague={passNewlyCreatedLeague}/>
        </>
      )}

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

      {selectedLeague && (
        <>
          {showAddPlayerInput && (
            <input
              placeholder="First Last"
              className="text-input"
              onChange={(e) => {
                handleAddPlayerNameChange(e);
              }}
            />
          )}
          <div className="flex-buttons">
            <button onClick={handlePlayerAddition}>Add Player</button>
            <button onClick={eventTriggerClick}>Create Event</button>
          </div>
        </>
      )}
    </div>
  );
}

export default LeagueSelection;
