package com.evs.main.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.evs.main.model.Application;
import com.evs.main.model.Candidate;
import com.evs.main.model.Election;
import com.evs.main.model.Party;
import com.evs.main.model.Result;
import com.evs.main.model.UserCredentials;
import com.evs.main.model.VoterDetails;
import com.evs.main.service.AdministratorService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/evs/admin")
public class AdministratorController {
    private AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
    
    // Authentication operation
	@GetMapping("/authenticateUser/{userID}/{password}")
	@ResponseBody
	public boolean authenticate(@PathVariable String userID,@PathVariable String password)
	{
		try {
			UserCredentials userCredentials=new UserCredentials();
			userCredentials.setUserId(userID);
			userCredentials.setPassword(password);
			return administratorService.authenticate(userCredentials);
		} catch (Exception e) {
            System.err.println("Error authenticating user: " + e.getMessage());
            return false;
        }
	}
    
    // Application CRUD operations
    @PostMapping("/addApplication")
    public ResponseEntity<Application> saveApplication(@RequestBody Application application) {
        try {
        	administratorService.saveApplication(application);
            return new ResponseEntity<>(application, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving application: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateApplication")
    public ResponseEntity<Application> updateApplication(@RequestBody Application application) {
        try {
        	administratorService.updateApplication(application);
            return new ResponseEntity<>(application, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating application: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteApplication/{id}")
    public ResponseEntity<Map<String, String>> deleteApplication(@PathVariable(value = "id") String userId) {
        try {
        	administratorService.deleteApplication(userId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Application deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting application: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectApplication/{id}")
    public ResponseEntity<Application> findApplicationById(@PathVariable(value = "id") String userId) {
        try {
            Application application = administratorService.findApplicationById(userId);
            if (application != null) {
                return new ResponseEntity<>(application, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding application by ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectApplication")
    public List<Application> findAllApplications() {
        try {
            List<Application> applications = administratorService.findAllApplications();
            return applications;
        } catch (Exception e) {
            System.err.println("Error finding all applications: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // Candidate CRUD operations
    @PostMapping("/addCandidate")
    public ResponseEntity<Candidate> saveCandidate(@RequestBody Candidate candidate) {
        try {
        	administratorService.saveCandidate(candidate);
            return new ResponseEntity<>(candidate, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving candidate: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCandidate")
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate) {
        try {
        	administratorService.updateCandidate(candidate);
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating candidate: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCandidate/{id}")
    public ResponseEntity<Map<String, String>> deleteCandidate(@PathVariable(value = "id") String candidateId) {
        try {
        	administratorService.deleteCandidate(candidateId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Candidate deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting candidate: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectCandidate/{id}")
    public ResponseEntity<Candidate> findCandidateById(@PathVariable(value = "id") String candidateId) {
        try {
        	Candidate candidate = administratorService.findCandidateById(candidateId);
            if (candidate != null) {
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding candidate by ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectCandidate")
    public List<Candidate> findAllCandidates() {
        try {
            List<Candidate> candidates = administratorService.findAllCandidates();
            return candidates;
        } catch (Exception e) {
            System.err.println("Error finding all candidates: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    @GetMapping("/selectCandidatesByPartyId/{id}")
    public ResponseEntity<List<Candidate>> findCandidatesByPartyId(@PathVariable(value = "id") String partyId) {
        try {
            List<Candidate> candidates = administratorService.findCandidatesByPartyId(partyId);
            return new ResponseEntity<>(candidates, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while fetching candidates by party ID.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectCandidatesByElectionName/{name}/{constituency}/{id}")
    public ResponseEntity<List<Candidate>> findCandidatesByElectionName(@PathVariable(value = "name") String name, @PathVariable(value = "constituency") String constituency, @PathVariable(value = "id") String electionId) {
        try {
            List<Candidate> candidates = administratorService.findCandidatesByElectionName(name, constituency, electionId);
            return new ResponseEntity<>(candidates, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while fetching candidates by election name and constituency.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Election CRUD operations
    @PostMapping("/addElection")
    public ResponseEntity<Election> saveElectoralOfficer(@RequestBody Election election) {
        try {
        	administratorService.saveElection(election);
            return new ResponseEntity<>(election, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving election: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateElection")
    public ResponseEntity<Election> updateElection(@RequestBody Election election) {
        try {
        	administratorService.updateElection(election);
            return new ResponseEntity<>(election, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating election: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteElection/{id}")
    public ResponseEntity<Map<String, String>> deleteElection(@PathVariable(value = "id") String officerId) {
        try {
        	administratorService.deleteElection(officerId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Election deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting election: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectElection/{id}")
    public ResponseEntity<Election> findElectionById(@PathVariable(value = "id") String electionId) {
        try {
            Election election = administratorService.findElectionById(electionId);
            if (election != null) {
                return new ResponseEntity<>(election, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding election: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectElection")
    public List<Election> findAllElections() {
        try {
            List<Election> elections = administratorService.findAllElections();
            return elections;
        } catch (Exception e) {
            System.err.println("Error finding all elections: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // Party CRUD operations
    @PostMapping("/addParty")
    public ResponseEntity<Party> saveParty(@RequestBody Party party) {
        try {
        	administratorService.saveParty(party);
            return new ResponseEntity<>(party, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving party: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateParty")
    public ResponseEntity<Party> updateParty(@RequestBody Party party) {
        try {
        	administratorService.updateParty(party);
            return new ResponseEntity<>(party, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating party: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteParty/{id}")
    public ResponseEntity<Map<String, String>> deleteParty(@PathVariable(value = "id") String partyId) {
        try {
        	administratorService.deleteParty(partyId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Party deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting party: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectParty/{id}")
    public ResponseEntity<Party> findPartyById(@PathVariable(value = "id") String partyId) {
        try {
        	Party party = administratorService.findPartyById(partyId);
            if (party != null) {
                return new ResponseEntity<>(party, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding party: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectParty")
    public List<Party> findAllPartys() {
        try {
            List<Party> partys = administratorService.findAllPartys();
            return partys;
        } catch (Exception e) {
            System.err.println("Error finding all parties: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // Result CRUD operations
    @PostMapping("/addResult")
    public ResponseEntity<Result> saveResult(@RequestBody Result result) {
        try {
        	administratorService.saveResult(result);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving result: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateResult")
    public ResponseEntity<Result> updateResult(@RequestBody Result result) {
        try {
        	administratorService.updateResult(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating result: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteResult/{id}")
    public ResponseEntity<Map<String, String>> deleteResult(@PathVariable(value = "id") String serialNo) {
        try {
        	administratorService.deleteResult(serialNo);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Result deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting result: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectResult/{id}")
    public ResponseEntity<Result> findResultById(@PathVariable(value = "id") String serialNo) {
        try {
        	Result result = administratorService.findResultById(serialNo);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding result: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectResult")
    public List<Result> findAllResults() {
        try {
            List<Result> result = administratorService.findAllResults();
            return result;
        } catch (Exception e) {
            System.err.println("Error finding all results: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // VoterDetails CRUD operations
    @PostMapping("/addVD")
    public ResponseEntity<VoterDetails> saveVoterDetails(@RequestBody VoterDetails voterDetails) {
        try {
        	administratorService.saveVoterDetails(voterDetails);
            return new ResponseEntity<>(voterDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving voter details: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateVD")
    public ResponseEntity<VoterDetails> updateVoterDetails(@RequestBody VoterDetails voterDetails) {
        try {
        	administratorService.updateVoterDetails(voterDetails);
            return new ResponseEntity<>(voterDetails, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating voter details: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteVD/{id}")
    public ResponseEntity<Map<String, String>> deleteVoterDetails(@PathVariable(value = "id") String serialNo) {
        try {
        	administratorService.deleteVoterDetails(serialNo);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Voter details deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting voter details: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectVD/{id}")
    public ResponseEntity<VoterDetails> findVoterDetailsById(@PathVariable(value = "id") String serialNo) {
        try {
        	VoterDetails voterDetails = administratorService.findVoterDetailsById(serialNo);
            if (voterDetails != null) {
                return new ResponseEntity<>(voterDetails, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding voter details: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectVD")
    public List<VoterDetails> findAllVoterDetails() {
        try {
            List<VoterDetails> voterDetailsList = administratorService.findAllVoterDetails();
            return voterDetailsList;
        } catch (Exception e) {
            System.err.println("Error finding all voter details: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}