import React, { useEffect, useState } from "react";
import "./Standings.css";
import requests from "../requests";
import axios from "../axios";
import EventTable from './EventTable';

function Standings() {
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
    let check = { selected: false };
    axios
      .get(`${requests.fetchLeagues}/${e.target.value}/standings`)
      .then((response) => {
        response.data.forEach((playerStanding, index) => {
          response.data[index] = {
            ...playerStanding,
            ...check,
          };
        });
        setLeagueSelect(response.data);
      });
  };

  // called when a check-box is selected for event check-in
  const playerCheckIn = (item) => {
    leagueSelect.map(playerStanding => {
      if (playerStanding.player.id === item.player.id) {
        playerStanding.selected ? playerStanding.selected = false : playerStanding.selected = true;
      }
      return playerStanding;
    })
    setPlayersCheckedIn(leagueSelect.filter((e) => e.selected));
    console.log(playersCheckedIn);
  };

  // called when Create Event button is clicked
  const eventTriggerClick = () => {
    setShowEventTable(!showEventTable);
  }

  return (
    <div>
      <h4>select league to see standings</h4>
      {/* Dropdown Select */}
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

      {/* Standings Table */}
      {leagueValue && (
        <table className="styled-table">
          <thead>
            {/* <tr>
            <th colSpan="2">{value}</th>
          </tr> */}
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
                    type='checkbox'
                    id={playerRow.player.id}
                    onChange={e => {playerCheckIn(playerRow)}}
                    />
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {/* Create Event Button */}
      {leagueValue && 
        <button
          onClick={eventTriggerClick}
        >
          Create Event
        </button>
      }

      {showEventTable && <EventTable playerList={playersCheckedIn}/>}

    </div>
  );
}

export default Standings;
