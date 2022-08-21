import React, { useEffect, useState } from "react";
import "./Standings.css";
import requests from "./requests";
import axios from "./axios";

function Standings() {
  const [leagues, setLeagues] = useState([]);
  const [value, setValue] = useState();
  const [leagueSelect, setLeagueSelect] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const request = await axios.get(`${requests.fetchLeagues}`);
      setLeagues(request.data);
      return request.data;
    }
    fetchData();
  }, []);

  const leagueChange = (e) => {
    setValue(e.target.value);
    axios
      .get(`${requests.fetchLeagues}/${e.target.value}/standings`)
      .then((response) => setLeagueSelect(response.data))
      .then((error) => console.log(error));
  };

  return (
    <div>
      <div className="custom-select">
        {/* <select value={value}> */}
        <select value={value} onChange={leagueChange}>
          <option key="0">-- Select League --</option>
          {leagues.map((league) => (
            <option key={league.id} value={league.id}>
              {league.name}
            </option>
          ))}
        </select>
      </div>
      {/* <StandingsTable /> */}
      <table className="styled-table">
        <thead>
          <tr>
            <th colspan="2">{value}</th>
          </tr>
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
    </div>
  );
}

export default Standings;
