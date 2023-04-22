package com.evs.main.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_Voter_Details")
public class VoterDetails {
	@Id
	@Column(name = "Serialno", length=6)
	private String serialno;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Candidateid", referencedColumnName = "Candidateid", insertable = false, updatable = false)
    private Candidate candidate;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Electionid", referencedColumnName = "Electionid", insertable = false, updatable = false)
    private Election election;
	
	@Column(name = "VoterId", length = 8, nullable = false)
    private String voterId;
	
	public VoterDetails() {
		
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
}