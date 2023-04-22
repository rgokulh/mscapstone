import axios from "axios";
import React, { useState } from "react";
import "./DeleteElection.css";

function DeleteElection() {
  const [electionID, setElectionID] = useState();
  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .delete(`http://localhost:8080/evs/admin/deleteElection/` + electionID)
      .then((res) => alert("Data is Deleted"));
    console.log("Election Deleted successfully..." + electionID);
  };
  return (
    <div>
      <div className="d">
        <div className="a">
          <h1> Delete Election</h1>
          <form onSubmit={handleSubmit}>
            Enter Election ID :{" "}
            <input
              type="text"
              value={electionID}
              onChange={(e) => {
                setElectionID(e.target.value);
              }}
            />
            <br />
            <button>Delete Election</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default DeleteElection;
