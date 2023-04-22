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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evs.main.model.ElectoralOfficer;
import com.evs.main.service.ElectoralOfficerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/evs/EO")
public class ElectoralOfficerController {
	private ElectoralOfficerService electoralOfficerService;

    @Autowired
    public ElectoralOfficerController(ElectoralOfficerService electoralOfficerService) {
        this.electoralOfficerService = electoralOfficerService;
    }
	
    // ElectoralOfficer CRUD operations
    @PostMapping("/addEO")
    public ResponseEntity<ElectoralOfficer> saveElectoralOfficer(@RequestBody ElectoralOfficer officer) {
        try {
        	electoralOfficerService.saveElectoralOfficer(officer);
            return new ResponseEntity<>(officer, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error saving electoral officer: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateEO")
    public ResponseEntity<ElectoralOfficer> updateElectoralOfficer(@RequestBody ElectoralOfficer officer) {
        try {
        	electoralOfficerService.updateElectoralOfficer(officer);
            return new ResponseEntity<>(officer, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating electoral officer: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEO/{id}")
    public ResponseEntity<Map<String, String>> deleteElectoralOfficer(@PathVariable(value = "id") String officerId) {
        try {
        	electoralOfficerService.deleteElectoralOfficer(officerId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Electoral officer deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error deleting electoral officer: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectEO/{id}")
    public ResponseEntity<ElectoralOfficer> findElectoralOfficerById(@PathVariable(value = "id") String officerId) {
        try {
            ElectoralOfficer officer = electoralOfficerService.findElectoralOfficerById(officerId);
            if (officer != null) {
                return new ResponseEntity<>(officer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error finding electoral officer by ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectEO")
    public List<ElectoralOfficer> findAllElectoralOfficers() {
        try {
            List<ElectoralOfficer> officers = electoralOfficerService.findAllElectoralOfficers();
            return officers;
        } catch (Exception e) {
            System.err.println("Error finding all electoral officers: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // Generate VoterId operation
    @GetMapping("/generateVoterId/{userId}")
    public ResponseEntity<String> generateVoterId(@PathVariable(value="userId") String userId, @RequestParam String status) {
        String result = electoralOfficerService.generateVoterId(userId, status);
        if ("SUCCESS".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if ("FAIL".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}