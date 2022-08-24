import "./EventTable.css";
import React from "react";
import requests from "../requests";
import axios from '../axios.js';

function EventTable({playerList}) {

  // called when a score is changed on EventTable
  const handleScoreChange = (e, playerID) => {
    playerList.map(playerItem => {
      if (playerItem.player.id === playerID) {
        playerItem.strokesToPar = parseInt(e.target.value);
      }
      return playerItem;
    })
  }
  
  // called when results are submitted
  async function handleSubmitResults() {
    console.log(playerList);
  
    // get an eventID after creating a new one!
    let newEvent = await axios
      .post(`${requests.fetchEvents}`, 
      {
        name: 'a new event!',
        date: new Date().toISOString().substring(0, 10)
      });

    // map a new list of ready eventResult objects and console log
    let resultsArray = [];
    playerList.forEach((playerResult) => {
      resultsArray.push({
        leagueID : playerResult.leagueID,
        eventID : newEvent.data.id,
        playerID : playerResult.player.id,
        totalStrokesToPar : playerResult.strokesToPar,
        incomingTag : playerResult.playerTag
      });
    })
    console.log(resultsArray);

    // POST resultsArray
    let resultsPosted = await axios.post(`${requests.fetchEventResults}/list`, resultsArray);
    console.log(resultsPosted);

    // add event name feature
  }

  return (
    <div>
      <table className="styled-table event-table">
        <thead>
          {/* <tr>
            <th colSpan="2">{value}</th>
          </tr> */}
          <tr>
            <th>Incoming<br />Tag #</th>
            <th>Name</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {playerList.map((playerRow) => (
            <tr key={playerRow.player.id}>
              <td>{playerRow.playerTag}</td>
              <td>
                {playerRow.player.firstName} {playerRow.player.lastName}
              </td>
              <td>
                <input 
                  type="text" 
                  className="text-input" 
                  maxLength="4"
                  onChange={e => {handleScoreChange(e, playerRow.player.id)}}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Create Event Button */}

        <button
          onClick={handleSubmitResults}
        >
          Submit Results
        </button>
            <br /><br />
    </div>
  );
}

export default EventTable;
