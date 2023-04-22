import axios from "axios";
import React, { useState } from "react";
import "./DeleteParty.css";

function DeleteParty() {
  const [partyID, setPartyID] = useState();
  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .delete(`http://localhost:8080/evs/admin/deleteParty/` + partyID)
      .then((res) => alert("Data is Deleted"));
    console.log("Party Deleted successfully..." + partyID);
  };
  return (
    <div>
      <div className="d">
        <div className="a">
          <h1> Delete Party</h1>
          <form onSubmit={handleSubmit}>
            Enter Party ID :{" "}
            <input
              type="text"
              value={partyID}
              onChange={(e) => {
                setPartyID(e.target.value);
              }}
            />
            <br />
            <button>Delete Party</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default DeleteParty;
