import React, { useState, useEffect } from "react";
import axios from "axios";
import "./SelectApplication.css";

function SelectApplication2() {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/evs/admin/selectApplication")
      .then((res) => {
        setData(res.data);
      });
  }, []);

  const filteredData = data.filter((item) => item.approvedstatus === 0);

  console.log(filteredData);

  return (
    <div>
      <div className="s1">
        <div className="s2">
          <table border="4">
            <thead>
              <tr>
                <th>User ID</th>
                <th>Passed Status</th>
                <th>Approved Status</th>
                <th>Constituency</th>
                <th>Voter ID</th>
              </tr>
            </thead>
            <tbody>
              {filteredData.map((item) => (
                <tr key={item.userId}>
                  <td>{item.userId}</td>
                  <td>{item.passedstatus}</td>
                  <td>{item.approvedstatus}</td>
                  <td>{item.constituency}</td>
                  <td>{item.voterId}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default SelectApplication2;
