package com.evs.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_EO")
public class ElectoralOfficer {
	
	@Id
	@Column(name="Electoralofficerid", length=6)
	private String electoralOfficerId;
	
	@Column(name = "Constituency", length = 25, nullable = false)
    private String constituency;
	
	public ElectoralOfficer() {
		
    }

	public String getElectoralOfficerId() {
		return electoralOfficerId;
	}

	public void setElectoralOfficerId(String electoralOfficerId) {
		this.electoralOfficerId = electoralOfficerId;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
}