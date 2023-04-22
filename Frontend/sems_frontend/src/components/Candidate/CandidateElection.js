import React, { useState } from "react";
import axios from "axios";
import "./CandidateElection.css";

function CandidateElection() {
  const [data, setData] = useState(null);
  const [electionName, setElectionName] = useState("");
  const [constituency, setConstituency] = useState("");
  const [electionId, setElectionId] = useState("");

  const handleElectionNameChange = (event) => {
    setElectionName(event.target.value);
  };

  const handleConstituencyChange = (event) => {
    setConstituency(event.target.value);
  };

  const handleElectionIdChange = (event) => {
    setElectionId(event.target.value);
  };

  const handleSubmit = () => {
    axios
      .get(
        `http://localhost:8080/evs/admin/selectCandidatesByElectionName/${electionName}/${constituency}/${electionId}`
      )
      .then((res) => {
        if (res.data && res.data.length > 0) {
          setData(res.data);
          alert("Data fetched successfully");
        } else {
          console.error("No candidate data received:", res.data);
          alert("Failed to fetch candidate data. Please try again later.");
        }
      })
      .catch((error) => {
        console.error("Error while fetching data:", error);
        alert("Failed to retrieve any data. Please try again later.");
      });
  };

  return (
    <div>
      <div className="s1">
        <div className="s2">
          <label htmlFor="electionName">Enter the election name:</label>
          <input
            type="text"
            id="electionName"
            value={electionName}
            onChange={handleElectionNameChange}
          />
          <label htmlFor="constituency">Enter the constituency:</label>
          <input
            type="text"
            id="constituency"
            value={constituency}
            onChange={handleConstituencyChange}
          />
          <label htmlFor="electionId">Enter the election ID:</label>
          <input
            type="text"
            id="electionId"
            value={electionId}
            onChange={handleElectionIdChange}
          />
          <button onClick={handleSubmit}>Submit</button>
          <table border="4">
            <thead>
              <tr>
                <th>Candidate ID</th>
                <th>Name</th>
                <th>Election ID</th>
                <th>Election Name</th>
                <th>Constituency</th>
                <th>Party ID</th>
                <th>Party Name</th>
                <th>Date of Birth</th>
                <th>Mobile No</th>
                <th>Address</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              {data &&
                data.map((item) => (
                  <tr key={item.candidateid}>
                    <td>{item.candidateid}</td>
                    <td>{item.name}</td>
                    <td>{item.election.electionid}</td>
                    <td>{item.election.name}</td>
                    <td>{item.constituency}</td>
                    <td>{item.party.partyid}</td>
                    <td>{item.party.name}</td>
                    <td>{item.dateofbirth}</td>
                    <td>{item.mobileno}</td>
                    <td>{item.address}</td>
                    <td>{item.email}</td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default CandidateElection;
