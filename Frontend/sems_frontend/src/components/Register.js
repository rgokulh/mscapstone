import React, { useState } from "react";
import axios from "axios";
import "./Register.css";
import { useNavigate } from "react-router-dom";

function Register() {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");

  const nav = useNavigate();

  const checkUserExists = async (userId) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/evs/voter/selectCredentials/${userId}`
      );
      return response.data;
    } catch (error) {
      console.error("Error checking user existence:", error);
      return false;
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userExists = await checkUserExists(userId);

    if (userExists) {
      alert("User already exists");
    } else {
      let obj = {
        userId: userId,
        password: password,
        userType: userType,
        loginStatus: 0,
      };
      console.log(obj);
      axios
        .post("http://localhost:8080/evs/voter/addCredentials", obj)
        .then((res) => {
          alert("User is added");
          if (userType.toUpperCase() === "V") {
            nav("/addprofile");
          }
        })
        .catch((error) => {
          console.error("Error adding user:", error);
          alert("Error adding user");
        });
    }
  };

  return (
    <div>
      <div className="g">
        <div className="h">
          <h1>USER REGISTRATION</h1>
          <form onSubmit={handleSubmit}>
            Enter your user id :{" "}
            <input
              type="text"
              value={userId}
              onChange={(e) => {
                setUserId(e.target.value);
              }}
            />
            <br />
            Enter your password :{" "}
            <input
              type="text"
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
              }}
            />
            <br />
            Enter the user type - A/E/V:{" "}
            <input
              type="text"
              value={userType}
              onChange={(e) => {
                setUserType(e.target.value);
              }}
            />
            <br />
            <button>Register</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Register;
