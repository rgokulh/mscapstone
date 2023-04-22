package com.evs.main.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_Result")
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Serialno", length = 6)
	private int serialno;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Electionid", referencedColumnName = "Electionid", insertable = false, updatable = false)
    private Election election;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Candidateid", referencedColumnName = "Candidateid", insertable = false, updatable = false)
	private Candidate candidate;
	
	@Column(name = "Votecount", nullable = false)
	private int votecount;
	
	public Result() {
		
	}

	public int getSerialno() {
		return serialno;
	}

	public void setSerialno(int serialno) {
		this.serialno = serialno;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public int getVotecount() {
		return votecount;
	}

	public void setVotecount(int votecount) {
		this.votecount = votecount;
	}
}
