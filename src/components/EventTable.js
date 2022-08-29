import "./EventTable.css";
import React from "react";
import requests from "../requests";
import axios from "../axios.js";

function EventTable({ eventResults, playerList }) {
  let eventName = "";

  // called when a score is changed on EventTable
  const handleScoreChange = (e, playerID) => {
    playerList.map((playerItem) => {
      if (playerItem.player.id === playerID) {
        playerItem.strokesToPar = parseInt(e.target.value);
      }
      return playerItem;
    });
  };

  const handleEventNameChange = (e) => {
    eventName = e.target.value;
  };

  // called when results are submitted
  async function handleSubmitResults() {
    // get an eventID after creating a new one!
    let newEvent = await axios.post(`${requests.fetchEvents}`, {
      name: eventName,
      date: new Date().toISOString().substring(0, 10),
    });

    // map a new list of ready eventResult objects and console log
    let resultsArray = [];
    playerList.forEach((playerResult) => {
      resultsArray.push({
        leagueID: playerResult.leagueID,
        eventID: newEvent.data.id,
        playerID: playerResult.player.id,
        totalStrokesToPar: playerResult.strokesToPar,
        incomingTag: playerResult.playerTag,
      });
    });

    // POST resultsArray
    let resultsPosted = await axios.post(
      `${requests.fetchEventResults}/list`,
      resultsArray
    );
    eventResults(resultsPosted.data);
  }

  return (
    <div>
      <table className="styled-table event-table">
        <thead>
          {/* <tr>
            <th colSpan="2">{value}</th>
          </tr> */}
          <tr>
            <th>
              Incoming
              <br />
              Tag #
            </th>
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
                  onChange={(e) => {
                    handleScoreChange(e, playerRow.player.id);
                  }}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <input
        placeholder="Event Name"
        className="text-input event-input"
        onChange={(e) => {
          handleEventNameChange(e);
        }}
      />

      {/* Create Event Button */}
      <button onClick={handleSubmitResults}>Submit Results</button>
      <br />
      <br />
    </div>
  );
}

export default EventTable;
