import React, { useEffect, useState } from "react";
import "./AddProfile.css";
import axios from "axios";

function AddProfile() {
  const [userId, setUserId] = useState("");
  const [firstname, setFirstame] = useState("");
  const [lastname, setLastName] = useState("");
  const [dateofbirth, setDateofbirth] = useState("");
  const [gender, setGender] = useState("");
  const [street, setStreet] = useState("");
  const [location, setLocation] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [pincode, setPincode] = useState("");
  const [mobileno, setMobileno] = useState("");
  const [emailId, setEmailId] = useState("");
  const [userCredentialsUserId, setUserCredentialsUserId] = useState("");
  const [userCredentialsPassword, setUserCredentialsPassword] = useState("");
  const [userCredentialsType, setUserCredentialsType] = useState("");
  const [userCredentialsLoginStatus, setUserCredentialsLoginStatus] =
    useState("");

  useEffect(() => {
    if (userId) {
      fetchUserCredentials(userId);
    }
  }, [userId]);

  const fetchUserCredentials = async (userId) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/evs/voter/selectCredentials/${userId}`
      );
      const data = response.data;
      setUserCredentialsUserId(data.userId);
      setUserCredentialsPassword(data.password);
      setUserCredentialsType(data.userType);
      setUserCredentialsLoginStatus(data.loginStatus);
    } catch (error) {
      console.error("Error fetching user credentials:", error);
    }
  };

  const [obj, setObj] = useState({});
  const handeSubmit = (e) => {
    e.preventDefault();

    const newObj = {
      userId: userId,
      firstName: firstname,
      lastName: lastname,
      dateOfBirth: dateofbirth,
      gender: gender,
      street: street,
      location: location,
      city: city,
      state: state,
      pincode: pincode,
      mobileNo: mobileno,
      emailId: emailId,
      userCredentials: {
        userId: userCredentialsUserId,
        password: userCredentialsPassword,
        userType: userCredentialsType,
        loginStatus: userCredentialsLoginStatus,
      },
    };
    console.log(newObj);
    axios
      .post("http://localhost:8080/evs/voter/addProfile", newObj)
      .then((response) => {
        alert("Data is added successfully");
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className="containerprofile">
      <div className="b1">
        <div className="b2">
          <h1>Voter Registration</h1>
          <form onSubmit={handeSubmit}>
            Enter User ID :{" "}
            <input
              type="text"
              value={userId}
              onChange={(e) => {
                setUserId(e.target.value);
              }}
            />
            <br />
            Enter First Name :{" "}
            <input
              type="text"
              value={firstname}
              onChange={(e) => {
                setFirstame(e.target.value);
              }}
            />
            <br />
            Enter Last Name :{" "}
            <input
              type="text"
              value={lastname}
              onChange={(e) => {
                setLastName(e.target.value);
              }}
            />
            <br />
            Enter Date Of Birth:{" "}
            <input
              type="text"
              value={dateofbirth}
              onChange={(e) => {
                setDateofbirth(e.target.value);
              }}
            />
            <br />
            Enter Gender:{" "}
            <input
              type="text"
              value={gender}
              onChange={(e) => {
                setGender(e.target.value);
              }}
            />
            <br />
            Enter Street:{" "}
            <input
              type="text"
              value={street}
              onChange={(e) => {
                setStreet(e.target.value);
              }}
            />
            <br />
            Enter Location:{" "}
            <input
              type="text"
              value={location}
              onChange={(e) => {
                setLocation(e.target.value);
              }}
            />
            <br />
            Enter City:{" "}
            <input
              type="text"
              value={city}
              onChange={(e) => {
                setCity(e.target.value);
              }}
            />
            <br />
            Enter State:{" "}
            <input
              type="text"
              value={state}
              onChange={(e) => {
                setState(e.target.value);
              }}
            />
            <br />
            Enter Pincode:{" "}
            <input
              type="text"
              value={pincode}
              onChange={(e) => {
                setPincode(e.target.value);
              }}
            />
            <br />
            Enter Mobile No:{" "}
            <input
              type="text"
              value={mobileno}
              onChange={(e) => {
                setMobileno(e.target.value);
              }}
            />
            <br />
            Enter Email:{" "}
            <input
              type="email"
              value={emailId}
              onChange={(e) => {
                setEmailId(e.target.value);
              }}
            />
            <br />
            <button type="submit">Register</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddProfile;
