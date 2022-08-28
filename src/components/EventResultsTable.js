import React, {useEffect, useState} from "react";

function EventResultsTable({ eventResults }) {
  const [eventName, setEventName] = useState("");

  useEffect(() => {
    if (eventResults.length > 0) {
      setEventName(eventResults[0].eventName);
    }
  }, [eventResults]);

  return (
    <div>
      <h2>{eventName}</h2>
      <table className="styled-table results-table">
        <thead>
          <tr>
            <th>Outgoing<br />Tag #</th>
            <th>Name</th>
            <th>Total<br />Strokes</th>
          </tr>
        </thead>
        <tbody>
          {eventResults.map((playerRow) => (
            <tr key={playerRow.eventResultsID}>
              <td>{playerRow.outgoingTag}</td>
              <td>{playerRow.firstName} {playerRow.lastName}</td>
              <td>{playerRow.totalStrokesToPar}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default EventResultsTable;
