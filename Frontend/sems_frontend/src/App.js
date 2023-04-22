import "./App.css";
import { Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./components/Home";
import Register from "./components/Register";
import SelectApplication from "./components/Application/SelectApplication";
import SelectApplication1 from "./components/Application/SelectApplication1";
import SelectApplication2 from "./components/Application/SelectApplication2";
import AddApplication from "./components/Application/AddApplication";
import AddParty from "./components/Party/AddParty";
import DeleteParty from "./components/Party/DeleteParty";
import SelectParty from "./components/Party/SelectParty";
import UpdateParty from "./components/Party/UpdateParty";
import AddElection from "./components/Election/AddElection";
import DeleteElection from "./components/Election/DeleteElection";
import SelectElection from "./components/Election/SelectElection.js";
import SelectElection1 from "./components/Election/SelectElection1.js";
import UpdateElection from "./components/Election/UpdateElection";
import GenerateVoterId from "./components/EO/GenerateVoterId";
import AddProfile from "./components/Profile/AddProfile";
import CandidateParty from "./components/Candidate/CandidateParty";
import CandidateElection from "./components/Candidate/CandidateElection";

function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/register" element={<Register />}></Route>
        <Route
          path="/selectapplication"
          element={<SelectApplication />}
        ></Route>
        <Route
          path="/selectapplication1"
          element={<SelectApplication1 />}
        ></Route>
        <Route
          path="/selectapplication2"
          element={<SelectApplication2 />}
        ></Route>
        <Route path="/addprofile" element={<AddProfile />}></Route>
        <Route path="/addapplication" element={<AddApplication />}></Route>
        <Route path="/addparty" element={<AddParty />}></Route>
        <Route path="/updateparty" element={<UpdateParty />}></Route>
        <Route path="/deleteparty" element={<DeleteParty />}></Route>
        <Route path="/selectparty" element={<SelectParty />}></Route>
        <Route path="/addelection" element={<AddElection />} />
        <Route path="/updateelection" element={<UpdateElection />}></Route>
        <Route path="/deleteelection" element={<DeleteElection />}></Route>
        <Route path="/selectelection" element={<SelectElection />}></Route>
        <Route path="/selectelection1" element={<SelectElection1 />}></Route>
        <Route path="/generatevoterid" element={<GenerateVoterId />}></Route>
        <Route path="/candidateparty" element={<CandidateParty />}></Route>
        <Route
          path="/candidateelection"
          element={<CandidateElection />}
        ></Route>
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
