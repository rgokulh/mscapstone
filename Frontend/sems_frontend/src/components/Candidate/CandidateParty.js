import React, { useState } from "react";
import axios from "axios";
import "./CandidateParty.css";

function CandidateParty() {
  const [data, setData] = useState(null);
  const [partyid, setPartyid] = useState("");

  const handlePartyidChange = (event) => {
    setPartyid(event.target.value);
  };

  const handleSubmit = () => {
    axios
      .get(
        `http://localhost:8080/evs/admin/selectCandidatesByPartyId/${partyid}`
      )
      .then((res) => {
        if (res.data && res.data.length > 0) {
          setData(res.data);
          alert("Party id submitted successfully");
        } else {
          console.error("No party data retrieved:", res.data);
          alert("No data found for the given party ID. Please try again.");
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
          <label htmlFor="partyid">Enter the party ID:</label>
          <input
            type="text"
            id="partyid"
            value={partyid}
            onChange={handlePartyidChange}
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

export default CandidateParty;
