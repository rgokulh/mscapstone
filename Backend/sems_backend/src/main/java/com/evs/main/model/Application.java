package com.evs.main.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_Application")
public class Application {
	@Id
    @Column(name = "Userid", length = 6)
    private String userId;

    @Column(name = "Constituency", length = 20, nullable = false)
    private String constituency;

    @Column(name = "Passedstatus")
    private int passedstatus;

    @Column(name = "Approvedstatus")
    private int approvedstatus;
    
    @Column(name = "VoterId", length = 8)
    private String voterId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Userid", referencedColumnName = "Userid", insertable = false, updatable = false)
    private UserCredentials userCredentials;
    
    public Application() {
    	
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public int getPassedstatus() {
		return passedstatus;
	}

	public void setPassedstatus(int passedstatus) {
		this.passedstatus = passedstatus;
	}

	public int getApprovedstatus() {
		return approvedstatus;
	}

	public void setApprovedstatus(int approvedstatus) {
		this.approvedstatus = approvedstatus;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
}