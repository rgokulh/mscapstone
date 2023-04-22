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
@Table(name = "EVS_TBL_User_Profile")
public class UserProfile {

    @Id
    @Column(name = "Userid", length = 6)
    private String userId;

    @Column(name = "Firstname", length = 15, nullable = false)
    private String firstName;

    @Column(name = "Lastname", length = 15, nullable = false)
    private String lastName;

    @Column(name = "Dateofbirth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "Gender", length = 7, nullable = false)
    private String gender;

    @Column(name = "Street", length = 30, nullable = false)
    private String street;

    @Column(name = "Location", length = 15, nullable = false)
    private String location;

    @Column(name = "City", length = 15, nullable = false)
    private String city;

    @Column(name = "State", length = 15, nullable = false)
    private String state;

    @Column(name = "Pincode", length = 10, nullable = false)
    private String pincode;

    @Column(name = "MobileNo", length = 10)
    private String mobileNo;

    @Column(name = "EmailId", length = 30)
    private String emailId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Userid", referencedColumnName = "Userid", insertable = false, updatable = false)
    private UserCredentials userCredentials;
    
    public UserProfile() {
    	
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
}