package com.evs.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evs.main.model.Application;
import com.evs.main.model.Candidate;
import com.evs.main.model.Election;
import com.evs.main.model.Party;
import com.evs.main.model.Result;
import com.evs.main.model.UserCredentials;
import com.evs.main.model.VoterDetails;
import com.evs.main.repository.AdministratorRepository;

@Service
public class AdministratorService {
    private AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }
    
    // Authentication operation
    public boolean authenticate(UserCredentials userCredentials) throws Exception {
    	try {
    		return administratorRepository.authenticate(userCredentials);
    	} catch (Exception e) {
    	    throw new Exception("Error authenticating user with ID: " + userCredentials.getUserId(), e);
    	}
    }
    
    // Application CRUD operations
    public void saveApplication(Application application) throws Exception {
        try {
        	administratorRepository.saveApplication(application);
        } catch (Exception e) {
            throw new Exception("Error saving application", e);
        }
    }

    public void updateApplication(Application application) throws Exception {
        try {
        	administratorRepository.updateApplication(application);
        } catch (Exception e) {
            throw new Exception("Error updating application", e);
        }
    }

    public void deleteApplication(String userId) throws Exception {
        try {
        	administratorRepository.deleteApplication(userId);
        } catch (Exception e) {
            throw new Exception("Error deleting application", e);
        }
    }

    public Application findApplicationById(String userId) throws Exception {
        try {
            return administratorRepository.findApplicationById(userId);
        } catch (Exception e) {
            throw new Exception("Error finding application", e);
        }
    }

    public List<Application> findAllApplications() throws Exception {
        try {
            return administratorRepository.findAllApplications();
        } catch (Exception e) {
            throw new Exception("Error finding all applications", e);
        }
    }
    
    // Candidate CRUD operations
    public void saveCandidate(Candidate candidate) throws Exception {
        try {
        	administratorRepository.saveCandidate(candidate);
        } catch (Exception e) {
            throw new Exception("Error saving application", e);
        }
    }

    public void updateCandidate(Candidate candidate) throws Exception {
        try {
        	administratorRepository.updateCandidate(candidate);
        } catch (Exception e) {
            throw new Exception("Error updating candidate", e);
        }
    }

    public void deleteCandidate(String candidateId) throws Exception {
        try {
        	administratorRepository.deleteCandidate(candidateId);
        } catch (Exception e) {
            throw new Exception("Error deleting candidate", e);
        }
    }

    public Candidate findCandidateById(String candidateId) throws Exception {
        try {
            return administratorRepository.findCandidateById(candidateId);
        } catch (Exception e) {
            throw new Exception("Error finding candidate", e);
        }
    }

    public List<Candidate> findAllCandidates() throws Exception {
        try {
            return administratorRepository.findAllCandidates();
        } catch (Exception e) {
            throw new Exception("Error finding all candidates", e);
        }
    }
    
    public ArrayList<Candidate> findCandidatesByPartyId(String partyId) {
        ArrayList<Candidate> candidates = null;
        try {
            candidates = administratorRepository.findCandidatesByPartyId(partyId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while fetching candidates by party ID.");
        }
        return candidates;
    }

    public ArrayList<Candidate> findCandidatesByElectionName(String name, String constituency, String electionId) {
        ArrayList<Candidate> candidates = null;
        try {
            candidates = administratorRepository.findCandidatesByElectionName(name, constituency, electionId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while fetching candidates by election name and constituency.");
        }
        return candidates;
    }
    
    // Election CRUD operations	
    public void saveElection(Election election) throws Exception {
        try {
            administratorRepository.saveElection(election);
        } catch (Exception e) {
            throw new Exception("Error saving the election details", e);
        }
    }

    public void updateElection(Election election) throws Exception {
        try {
        	administratorRepository.updateElection(election);
        } catch (Exception e) {
            throw new Exception("Error updating the election details", e);
        }
    }

    public void deleteElection(String electionId) throws Exception {
        try {
        	administratorRepository.deleteElection(electionId);
        } catch (Exception e) {
            throw new Exception("Error deleting the election details", e);
        }
    }

    public Election findElectionById(String electionId) throws Exception {
        try {
            return administratorRepository.findElectionById(electionId);
        } catch (Exception e) {
            throw new Exception("Error finding the election details by ID", e);
        }
    }

    public List<Election> findAllElections() throws Exception {
        try {
            return administratorRepository.findAllElections();
        } catch (Exception e) {
            throw new Exception("Error finding all election details", e);
        }
    }
    
    // Party CRUD operations	
    public void saveParty(Party party) throws Exception {
        try {
        	administratorRepository.saveParty(party);
        } catch (Exception e) {
            throw new Exception("Error saving the party", e);
        }
    }

    public void updateParty(Party party) throws Exception {
        try {
        	administratorRepository.updateParty(party);
        } catch (Exception e) {
            throw new Exception("Error updating the party", e);
        }
    }

    public void deleteParty(String partyId) throws Exception {
        try {
        	administratorRepository.deleteParty(partyId);
        } catch (Exception e) {
            throw new Exception("Error deleting the party", e);
        }
    }

    public Party findPartyById(String partyId) throws Exception {
        try {
            return administratorRepository.findPartyById(partyId);
        } catch (Exception e) {
            throw new Exception("Error finding the party by ID", e);
        }
    }

    public List<Party> findAllPartys() throws Exception {
        try {
            return administratorRepository.findAllPartys();
        } catch (Exception e) {
            throw new Exception("Error finding all parties", e);
        }
    }
    
    // Result CRUD operations	
    public void saveResult(Result result) throws Exception {
        try {
        	administratorRepository.saveResult(result);
        } catch (Exception e) {
            throw new Exception("Error saving the result", e);
        }
    }

    public void updateResult(Result result) throws Exception {
        try {
        	administratorRepository.updateResult(result);
        } catch (Exception e) {
            throw new Exception("Error updating the result", e);
        }
    }

    public void deleteResult(String serialNo) throws Exception {
        try {
        	administratorRepository.deleteResult(serialNo);
        } catch (Exception e) {
            throw new Exception("Error deleting the result", e);
        }
    }

    public Result findResultById(String serialNo) throws Exception {
        try {
            return administratorRepository.findResultById(serialNo);
        } catch (Exception e) {
            throw new Exception("Error finding the party by ID", e);
        }
    }

    public List<Result> findAllResults() throws Exception {
        try {
            return administratorRepository.findAllResults();
        } catch (Exception e) {
            throw new Exception("Error finding all results", e);
        }
    }
    
    // VoterDetails CRUD operations	
    public void saveVoterDetails(VoterDetails voterDetails) throws Exception {
        try {
        	administratorRepository.saveVoterDetails(voterDetails);
        } catch (Exception e) {
            throw new Exception("Error saving the voter details", e);
        }
    }

    public void updateVoterDetails(VoterDetails voterDetails) throws Exception {
        try {
        	administratorRepository.updateVoterDetails(voterDetails);
        } catch (Exception e) {
            throw new Exception("Error updating the voter details", e);
        }
    }

    public void deleteVoterDetails(String serialNo) throws Exception {
        try {
        	administratorRepository.deleteVoterDetails(serialNo);
        } catch (Exception e) {
            throw new Exception("Error deleting the voter details", e);
        }
    }

    public VoterDetails findVoterDetailsById(String serialNo) throws Exception {
        try {
            return administratorRepository.findVoterDetailsById(serialNo);
        } catch (Exception e) {
            throw new Exception("Error finding the voter details by ID", e);
        }
    }

    public List<VoterDetails> findAllVoterDetails() throws Exception {
        try {
            return administratorRepository.findAllVoterDetails();
        } catch (Exception e) {
            throw new Exception("Error finding all voter details", e);
        }
    }
}