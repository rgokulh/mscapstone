import React, { useState } from "react";
import axios from "axios";
import "./GenerateVoterId.css";

function GenerateVoterId() {
  const [userId, setUserId] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .get(
        `http://localhost:8080/evs/EO/generateVoterId/${userId}?status=ACTIVE`
      )
      .then(() => alert("Application submitted successfully"))
      .catch(() =>
        alert("Failed to generate Voter ID. Please try again later.")
      );
  };

  return (
    <div className="container2">
      <h1>Voter ID Generation</h1>
      <form onSubmit={handleSubmit}>
        User Id:
        <input
          type="text"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
        />
        <br />
        <button type="submit">Generate Voter ID</button>
      </form>
    </div>
  );
}

export default GenerateVoterId;
