package com.evs.main.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evs.main.model.Application;
import com.evs.main.model.ElectoralOfficer;
import com.evs.main.model.UserProfile;
import com.evs.main.repository.AdministratorRepository;
import com.evs.main.repository.ElectoralOfficerRepository;
import com.evs.main.repository.VoterRepository;

@Service
public class ElectoralOfficerService {
    private final AdministratorRepository administratorRepository;
    private final ElectoralOfficerRepository electoralOfficerRepository;
    private final VoterRepository voterRepository;

    @Autowired
    public ElectoralOfficerService(AdministratorRepository administratorRepository, ElectoralOfficerRepository electoralOfficerRepository, VoterRepository voterRepository) {
        this.administratorRepository = administratorRepository;
        this.electoralOfficerRepository = electoralOfficerRepository;
        this.voterRepository = voterRepository;
    }
    
	// ElectoralOfficer CRUD operations	
    public void saveElectoralOfficer(ElectoralOfficer officer) throws Exception {
        try {
        	electoralOfficerRepository.saveElectoralOfficer(officer);
        } catch (Exception e) {
            throw new Exception("Error saving the electoral officer", e);
        }
    }

    public void updateElectoralOfficer(ElectoralOfficer officer) throws Exception {
        try {
        	electoralOfficerRepository.updateElectoralOfficer(officer);
        } catch (Exception e) {
            throw new Exception("Error updating the electoral officer", e);
        }
    }

    public void deleteElectoralOfficer(String officerId) throws Exception {
        try {
        	electoralOfficerRepository.deleteElectoralOfficer(officerId);
        } catch (Exception e) {
            throw new Exception("Error deleting the electoral officer", e);
        }
    }

    public ElectoralOfficer findElectoralOfficerById(String officerId) throws Exception {
        try {
            return electoralOfficerRepository.findElectoralOfficerById(officerId);
        } catch (Exception e) {
            throw new Exception("Error finding the electoral officer by ID", e);
        }
    }

    public List<ElectoralOfficer> findAllElectoralOfficers() throws Exception {
        try {
            return electoralOfficerRepository.findAllElectoralOfficers();
        } catch (Exception e) {
            throw new Exception("Error finding all electoral officers", e);
        }
    }
    
    // Generate VoterId operation
    public String generateVoterId(String userId, String status) {
        try {
        	
        	Application application = null;
        	UserProfile userProfile = null;

        	try {
        	    application = administratorRepository.findApplicationById(userId);
        	    userProfile = voterRepository.findProfileById(userId);
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}

            if (userProfile == null || status == null) {
                return "FAIL";
            }

            String firstName = userProfile.getFirstName();
            String constituencyName = application.getConstituency();

            String voterId = generateVoterIdString(firstName, constituencyName);
            application.setVoterId(voterId);
            application.setPassedstatus(1);
            application.setApprovedstatus(1);

            administratorRepository.updateApplication(application);
            
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    private String generateVoterIdString(String firstName, String constituencyName) {
        String firstTwoLettersFirstName = firstName.substring(0, 2).toUpperCase();
        String firstTwoLettersConstituencyName = constituencyName.substring(0, 2).toUpperCase();
        String randomNumber = generateRandomNumber();

        return firstTwoLettersFirstName + firstTwoLettersConstituencyName + randomNumber;
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(10000);
        return String.format("%04d", number);
    }
}