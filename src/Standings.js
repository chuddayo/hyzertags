import React, { useEffect, useState } from "react";
import "./Standings.css";
import requests from "./requests";
import axios from "./axios";

function Standings() {
  // initial component GET request for leagues list
  const [leagues, setLeagues] = useState([]);

  // keeps track of value of selected league in dropdown
  const [leagueValue, setLeagueValue] = useState("");

  // gets league standings upon selection
  const [leagueSelect, setLeagueSelect] = useState([]);

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
    axios
      .get(`${requests.fetchLeagues}/${e.target.value}/standings`)
      .then((response) => setLeagueSelect(response.data))
      .then((error) => console.log(error));
  };

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

      {/* Create Event Button */}
      {leagueValue && (<button>Create Event</button>)}

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
            </tr>
          </thead>
          <tbody>
            {leagueSelect.map((playerRow) => (
              <tr key={playerRow.player.id}>
                <td>{playerRow.playerTag}</td>
                <td>
                  {playerRow.player.firstName} {playerRow.player.lastName}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Standings;
