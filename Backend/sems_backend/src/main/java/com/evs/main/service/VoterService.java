package com.evs.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evs.main.model.UserCredentials;
import com.evs.main.model.UserProfile;
import com.evs.main.repository.VoterRepository;

@Service
public class VoterService {
    private VoterRepository voterRepository;

    @Autowired
    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    // UserCredentials CRUD operations
    public void saveCredentials(UserCredentials userCredentials) throws Exception {
        try {
            voterRepository.saveCredentials(userCredentials);
        } catch (Exception e) {
            throw new Exception("Error saving user credentials", e);
        }
    }

    public void updateCredentials(UserCredentials userCredentials) throws Exception {
        try {
            voterRepository.updateCredentials(userCredentials);
        } catch (Exception e) {
            throw new Exception("Error updating user credentials", e);
        }
    }

    public void deleteCredentials(String userId) throws Exception {
        try {
            voterRepository.deleteCredentials(userId);
        } catch (Exception e) {
            throw new Exception("Error deleting user credentials", e);
        }
    }

    public UserCredentials findCredentialsById(String userId) throws Exception {
        try {
            return voterRepository.findCredentialsById(userId);
        } catch (Exception e) {
            throw new Exception("Error finding user credentials by ID", e);
        }
    }

    public List<UserCredentials> findAllCredentials() throws Exception {
        try {
            return voterRepository.findAllCredentials();
        } catch (Exception e) {
            throw new Exception("Error finding all user credentials", e);
        }
    }

    // UserProfile CRUD operations
    public void saveProfile(UserProfile userProfile) throws Exception {
        try {
            voterRepository.saveProfile(userProfile);
        } catch (Exception e) {
            throw new Exception("Error saving user profile", e);
        }
    }

    public void updateProfile(UserProfile userProfile) throws Exception {
        try {
            voterRepository.updateProfile(userProfile);
        } catch (Exception e) {
            throw new Exception("Error updating user profile", e);
        }
    }

    public void deleteProfile(String userId) throws Exception {
        try {
            voterRepository.deleteProfile(userId);
        } catch (Exception e) {
            throw new Exception("Error deleting user profile", e);
        }
    }

    public UserProfile findProfileById(String userId) throws Exception {
        try {
            return voterRepository.findProfileById(userId);
        } catch (Exception e) {
            throw new Exception("Error finding user profile by ID", e);
        }
    }

    public List<UserProfile> findAllProfiles() throws Exception {
        try {
            return voterRepository.findAllProfiles();
        } catch (Exception e) {
            throw new Exception("Error finding all user profiles", e);
        }
    }  
}