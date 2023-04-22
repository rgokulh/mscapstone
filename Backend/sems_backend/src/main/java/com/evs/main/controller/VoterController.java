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
import org.springframework.web.bind.annotation.RestController;

import com.evs.main.model.UserCredentials;
import com.evs.main.model.UserProfile;
import com.evs.main.service.VoterService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/evs/voter")
public class VoterController {
    private VoterService voterService;

    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    // UserCredentials CRUD operations
    @PostMapping("/addCredentials")
    public ResponseEntity<UserCredentials> saveCredentials(@RequestBody UserCredentials userCredentials) {
        try {
            voterService.saveCredentials(userCredentials);
            return new ResponseEntity<>(userCredentials, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving user credentials: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCredentials")
    public ResponseEntity<UserCredentials> updateCredentials(@RequestBody UserCredentials userCredentials) {
        try {
            voterService.updateCredentials(userCredentials);
            return new ResponseEntity<>(userCredentials, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating user credentials: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCredentials/{id}")
    public ResponseEntity<Map<String, String>> deleteCredentials(@PathVariable(value = "id") String userId) {
        try {
            voterService.deleteCredentials(userId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User credentials deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting user credentials: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectCredentials/{id}")
    public ResponseEntity<UserCredentials> findCredentialsById(@PathVariable(value = "id") String userId) {
        try {
            UserCredentials userCredentials = voterService.findCredentialsById(userId);
            if (userCredentials != null) {
                return new ResponseEntity<>(userCredentials, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding user credentials by ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/selectCredentials")
    public List<UserCredentials> findAllCredentials() {
        try {
            List<UserCredentials> credentials = voterService.findAllCredentials();
            return credentials;
        } catch (Exception e) {
            System.err.println("Error finding all user profiles: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // UserProfile CRUD operations
    @PostMapping("/addProfile")
    public ResponseEntity<UserProfile> saveProfile(@RequestBody UserProfile userProfile) {
        try {
            voterService.saveProfile(userProfile);
            return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving user profile: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile userProfile) {
        try {
            voterService.updateProfile(userProfile);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating user profile: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteProfile/{id}")
    public ResponseEntity<Map<String, String>> deleteProfile(@PathVariable(value = "id") String userId) {
        try {
            voterService.deleteProfile(userId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User profile deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting user profile: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectProfile/{id}")
    public ResponseEntity<UserProfile> findProfileById(@PathVariable(value = "id") String userId) {
        try {
            UserProfile userProfile = voterService.findProfileById(userId);
            if (userProfile != null) {
                return new ResponseEntity<>(userProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding user profile by ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectProfile")
    public List<UserProfile> findAllProfiles() {
        try {
            List<UserProfile> profiles = voterService.findAllProfiles();
            return profiles;
        } catch (Exception e) {
            System.err.println("Error finding all user profiles: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}