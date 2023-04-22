package com.evs.main.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_Election")
public class Election {
	@Id
	@Column(name = "Electionid", length = 6)
	private String electionid;
	
	@Column(name = "Name", length = 15, nullable = false)
	private String name;
	
	@Column(name = "Electiondate", nullable = false)
	private Date electiondate;
	
	@Column(name = "District", length = 15, nullable = false)
	private String district;
	
	@Column(name = "Constituency", length = 15, nullable = false)
	private String constituency;
	
	@Column(name = "Countingdate", nullable = false)
	private Date countingdate;
	
	public Election() {
		
	}

	public String getElectionid() {
		return electionid;
	}

	public void setElectionid(String electionid) {
		this.electionid = electionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getElectiondate() {
		return electiondate;
	}

	public void setElectiondate(Date electiondate) {
		this.electiondate = electiondate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public Date getCountingdate() {
		return countingdate;
	}

	public void setCountingdate(Date countingdate) {
		this.countingdate = countingdate;
	}
}