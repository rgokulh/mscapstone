import React, { useState } from "react";
import axios from "axios";
import "./SelectParty.css";

function SelectParty() {
  const [data, setData] = useState([]);

  axios.get("http://localhost:8080/evs/admin/selectParty").then((res) => {
    setData(res.data);
  });

  console.log(data);

  return (
    <div>
      <div className="c">
        <div className="s">
          <table border="4">
            <thead>
              <tr>
                <th>PartyID</th>
                <th>Party Name</th>
                <th>Party Leader</th>
                <th>Party Symbol</th>
              </tr>
            </thead>
            <tbody>
              {data.map((item) => (
                <tr key={item.partyid}>
                  <td>{item.partyid}</td>
                  <td>{item.name}</td>
                  <td>{item.leader}</td>
                  <td>{item.symbol}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default SelectParty;
