import React, { useEffect, useState } from "react";
import axios from "axios";
import "./SelectElection.css";

function SelectElection1() {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/evs/admin/selectElection").then((res) => {
      setData(res.data);
    });
  }, []);

  useEffect(() => {
    const today = new Date();
    const filtered = data.filter((item) => {
      const electionDate = new Date(item.electiondate);
      return electionDate > today;
    });
    setFilteredData(filtered);
  }, [data]);

  return (
    <div>
      <div className="c">
        <div className="s">
          <table border="4">
            <thead>
              <tr>
                <th>ElectionID</th>
                <th>Name</th>
                <th>ElectionDate</th>
                <th>District</th>
                <th>Constituency</th>
                <th>CountingDate</th>
              </tr>
            </thead>
            <tbody>
              {filteredData.map((item) => (
                <tr key={item.electionid}>
                  <td>{item.electionid}</td>
                  <td>{item.name}</td>
                  <td>{item.electiondate}</td>
                  <td>{item.district}</td>
                  <td>{item.constituency}</td>
                  <td>{item.countingdate}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default SelectElection1;
