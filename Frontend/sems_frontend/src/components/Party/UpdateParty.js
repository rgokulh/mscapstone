import React, { useState } from "react";
import axios from "axios";
import "./UpdateParty.css";

function UpdateParty() {
  const [partyID, setPartyID] = useState();
  const [name, setName] = useState("");
  const [leader, setLeader] = useState("");
  const [symbol, setSymbol] = useState("");
  const [obj, setObj] = useState({});

  const handeSubmit = (e) => {
    e.preventDefault();
    const newObj = {
      partyid: partyID,
      name: name,
      leader: leader,
      symbol: symbol,
    };
    const ele = JSON.stringify(newObj);
    console.log(ele);
    console.log(newObj);

    axios
      .put("http://localhost:8080/evs/admin/updateParty", newObj)
      .then((res) => alert("Party updated successfully", res));
  };

  return (
    <div>
      <div className="x">
        <div className="y">
          <h1>Update Party</h1>
          <form onSubmit={handeSubmit}>
            Enter Party ID :{" "}
            <input
              type="text"
              value={partyID}
              onChange={(e) => {
                setPartyID(e.target.value);
              }}
            />
            <br />
            Enter Party Name :{" "}
            <input
              type="text"
              value={name}
              onChange={(e) => {
                setName(e.target.value);
              }}
            />
            <br />
            Enter Party Leader :{" "}
            <input
              type="text"
              value={leader}
              onChange={(e) => {
                setLeader(e.target.value);
              }}
            />
            <br />
            Enter Party Symbol :{" "}
            <input
              type="text"
              value={symbol}
              onChange={(e) => {
                setSymbol(e.target.value);
              }}
            />
            <br />
            <button>Update Party</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default UpdateParty;
