package com.evs.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVS_TBL_User_Credentials")
public class UserCredentials {

    @Id
    @Column(name = "Userid", length = 6)
    private String userId;

    @Column(name = "Password", length = 20, nullable = false)
    private String password;

    @Column(name = "Usertype", length = 1, nullable = false)
    private String userType;

    @Column(name = "Loginstatus")
    private int loginStatus;
    
    public UserCredentials() {
    	
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
}