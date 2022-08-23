import "./EventTable.css";
import React from "react";

function EventTable({playerList}) {

  return (
    <div>
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
          {playerList.map((playerRow) => (
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

export default EventTable;
