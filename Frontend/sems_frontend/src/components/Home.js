import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Home.css";
import { useNavigate } from "react-router-dom";

function Home() {
  const [userID, setUserID] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("Admin");
  let cb = {};
  const [error, setError] = useState("");

  const nav = useNavigate();
  const handleSubmit = (e) => {
    e.preventDefault();
    const data = { userID, password };
    console.log(JSON.stringify(data));

    axios
      .get(
        `http://localhost:8080/evs/admin/authenticateUser/${userID}/${password}`
      )
      .then((res) => {
        console.log(JSON.stringify(res.data));
        if (res.data === true) {
          if (role === "Admin") {
            if (userID === "AD-001" && password === "AD-001") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/addelection");
            } else if (userID === "AD-002" && password === "AD-002") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/selectelection1");
            } else if (userID === "AD-003" && password === "AD-003") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/selectelection");
            } else if (userID === "AD-004" && password === "AD-004") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/addparty");
            } else if (userID === "AD-005" && password === "AD-005") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/selectparty");
            } else if (userID === "AD-007" && password === "AD-007") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/candidateelection");
            } else if (userID === "AD-008" && password === "AD-008") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/selectapplication2");
            } else if (userID === "AD-009" && password === "AD-009") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/candidateparty");
            } else {
              setError("Invalid credentials");
            }
          } else if (role === "EO") {
            if (userID === "EO-001" && password === "EO-001") {
              alert("Logged In Successfully! Welcome Electoral Officer");
              nav("/selectapplication");
            } else if (userID === "EO-002" && password === "EO-002") {
              alert("Logged In Successfully! Welcome Electoral Officer");
              nav("/generatevoterid");
            } else {
              setError("Invalid credentials");
            }
          } else if (role === "Voter") {
            if (userID === "US-001" && password === "US-001") {
              alert("Logged In Successfully! Welcome Voter");
              nav("/register");
            } else if (userID === "US-002" && password === "US-002") {
              alert("Logged In Successfully! Welcome Voter");
              nav("/addapplication");
            } else if (userID === "US-003" && password === "US-003") {
              alert("Logged In Successfully! Welcome Voter");
              nav("/selectapplication1");
            } else if (userID === "US-004" && password === "US-004") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/selectelection1");
            } else if (userID === "US-005" && password === "US-005") {
              alert("Logged In Successfully! Welcome Administrator");
              nav("/candidateelection");
            } else {
              setError("Invalid credentials");
            }
          } else {
            setError("Invalid credentials");
          }
        }
      })
      .catch((err) => {
        console.error(err);
        setError("Error occurred while authenticating");
      });
  };

  return (
    <div>
      <div className="cont">
        <div className="image-container">
          <img
            src="./images/election-image.jpg"
            alt="Image"
            className="image1"
            width="700"
            height="300"
          />
          <p>
            India's election system is one of the largest and most complex in
            the world, with over 900 million eligible voters and more than a
            million polling stations. The country uses a first-past-the-post
            system to elect members of parliament, with parties needing to win a
            majority of seats to form a government.
          </p>
        </div>
        <div className="con">
          <h1>Login</h1>
          <form className="form-cont" onSubmit={handleSubmit}>
            Select Your Role :{" "}
            <select
              className="select-option"
              value={role}
              onChange={(e) => {
                setRole(e.target.value);
              }}
            >
              <option value="Admin">Admin</option>
              <option value="EO">EO</option>
              <option value="Voter">Voter</option>
            </select>
            <br />
            <br />
            Enter UserID :{" "}
            <input
              type="text"
              value={userID}
              onChange={(e) => {
                setUserID(e.target.value);
              }}
            />
            <br />
            Enter Password :{" "}
            <input
              type="text"
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
              }}
            />
            <button type="submit" className="bt1">
              Login
            </button>
            <br />
            <Link to="/register">
              <button className="bt2">Sign Up</button>
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Home;
