package com.evs.main.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_Candidate")
public class Candidate {
	@Id
	@Column(name = "Candidateid", length = 6)
	private String candidateid;
	
	@Column(name = "Name", length = 20, nullable = false)
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Electionid", referencedColumnName = "Electionid", insertable = false, updatable = false)
    private Election election;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Partyid", referencedColumnName = "Partyid", insertable = false, updatable = false)
    private Party party;
	
	@Column(name = "District", length = 20, nullable = false)
	private String district;
	
	@Column(name = "Constituency", length = 20, nullable = false)
	private String constituency;
	
	@Column(name = "Dateofbirth", nullable = false)
	private Date dateofbirth;
	
	@Column(name = "Mobileno", length = 10, nullable = false)
	private String mobileno;
	
	@Column(name = "Address", length = 50, nullable = false)
	private String address;
	
	@Column(name = "Email", length = 20)
	private String email;
	
	public Candidate() {
		
	}

	public String getCandidateid() {
		return candidateid;
	}

	public void setCandidateid(String candidateid) {
		this.candidateid = candidateid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
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

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}