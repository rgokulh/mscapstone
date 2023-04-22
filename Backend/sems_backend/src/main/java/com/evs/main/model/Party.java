package com.evs.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_Party")
public class Party {
	@Id
	@Column(name = "Partyid", length = 6)
	private String partyid;
	
	@Column(name = "Name", length = 20, nullable = false)
	private String name;
	
	@Column(name = "Leader", length = 20, nullable = false)
	private String leader;
	
	@Column(name = "Symbol", length = 40, nullable = false)
	private String symbol;
	
	public Party() {
		
	}

	public String getPartyid() {
		return partyid;
	}

	public void setPartyid(String partyid) {
		this.partyid = partyid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}