import axios from "../axios";
import React, { useState } from "react";
import requests from "../requests";

function LeagueCreation({passNewlyCreatedLeague}) {
  const [showNewLeagueInput, setShowNewLeagueInput] = useState(false);
  const [newLeagueName, setNewLeagueName] = useState("");

  const handleNewLeagueClick = () => {
    setShowNewLeagueInput(!showNewLeagueInput);
    if (showNewLeagueInput) {
      // call backend to create new league
      axios.post(`${requests.fetchLeagues}`, { name: newLeagueName })
        .then(response => passNewlyCreatedLeague(response.data.id))
      setNewLeagueName("");
    }
  };

  return (
    <div>
      <button className="btn-new-league" onClick={handleNewLeagueClick}>
        Create New League
      </button>
      {showNewLeagueInput && (
        <input
          className="text-input"
          placeholder="New League Name"
          onChange={(e) => {
            setNewLeagueName(e.target.value);
          }}
        />
      )}
    </div>
  );
}

export default LeagueCreation;
