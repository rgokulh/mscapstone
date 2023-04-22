import React, { useState } from "react";
import axios from "axios";
import "./Addparty.css";

function AddParty() {
  const [partyid, setPartyId] = useState("");
  const [name1, setName] = useState("");
  const [leader1, setLeader] = useState("");
  const [symbol1, setSymbol] = useState("");
  const [obj, setObj] = useState({});

  const handeSubmit = (e) => {
    e.preventDefault();

    const newObj = {
      partyid: partyid,
      name: name1,
      leader: leader1,
      symbol: symbol1,
    };

    axios
      .post("http://localhost:8080/evs/admin/addParty", newObj)
      .then((rej) => alert("Data is added successfully"));
  };
  return (
    <div>
      <div className="g">
        <div className="container2">
          <h1>Party Details</h1>
          <form onSubmit={handeSubmit}>
            Enter Party ID :{" "}
            <input
              type="text"
              value={partyid}
              onChange={(e) => {
                setPartyId(e.target.value);
              }}
            />
            <br />
            Enter Party Name :{" "}
            <input
              type="text"
              value={name1}
              onChange={(e) => {
                setName(e.target.value);
              }}
            />
            <br />
            Enter Party's Leader :{" "}
            <input
              type="text"
              value={leader1}
              onChange={(e) => {
                setLeader(e.target.value);
              }}
            />
            <br />
            Enter Party's Symbol :{" "}
            <input
              type="text"
              value={symbol1}
              onChange={(e) => {
                setSymbol(e.target.value);
              }}
            />
            <br />
            <button type="submit">Add Party</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddParty;
