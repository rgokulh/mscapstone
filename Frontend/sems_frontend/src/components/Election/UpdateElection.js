import React, { useState } from "react";
import axios from "axios";
import "./UpdateElection.css";

function UpdateElection() {
  const [electionid1, setElectionId] = useState("");
  const [name1, setName] = useState("");
  const [electiondate1, setElectionDate] = useState("");
  const [district1, setDistrict] = useState("");
  const [constituency1, setConstituency] = useState("");
  const [countingdate1, setCountingDate] = useState("");
  const [obj, setObj] = useState({});

  const handeSubmit = (e) => {
    e.preventDefault();
    const newObj = {
      electionid: electionid1,
      name: name1,
      electiondate: electiondate1,
      district: district1,
      constituency: constituency1,
      countingdate: countingdate1,
    };

    axios
      .put("http://localhost:8080/evs/admin/updateElection", newObj)
      .then((rej) => alert("Election updated successfully", rej));
  };

  return (
    <div>
      <div className="g">
        <div className="container2">
          <h1>Election Details</h1>
          <form onSubmit={handeSubmit}>
            Enter Election Id :{" "}
            <input
              type="text"
              value={electionid1}
              onChange={(e) => {
                setElectionId(e.target.value);
              }}
            />
            Enter Election Name :{" "}
            <input
              type="text"
              value={name1}
              onChange={(e) => {
                setName(e.target.value);
              }}
            />
            <br />
            Enter Election Date :{" "}
            <input
              type="text"
              value={electiondate1}
              onChange={(e) => {
                setElectionDate(e.target.value);
              }}
            />
            <br />
            Enter District :{" "}
            <input
              type="text"
              value={district1}
              onChange={(e) => {
                setDistrict(e.target.value);
              }}
            />
            <br />
            Enter Constituency :{" "}
            <input
              type="text"
              value={constituency1}
              onChange={(e) => {
                setConstituency(e.target.value);
              }}
            />
            <br />
            Enter Counting Date :{" "}
            <input
              type="text"
              value={countingdate1}
              onChange={(e) => {
                setCountingDate(e.target.value);
              }}
            />
            <br />
            <button type="submit">Update Election</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default UpdateElection;
