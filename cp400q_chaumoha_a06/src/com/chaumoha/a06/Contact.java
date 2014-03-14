package com.chaumoha.a06;

import java.util.UUID;

public class Contact {
	private UUID mID;
	private String mFirstName, mLastName, mPhoneNumber, mPhoto;
	
	
	public Contact(String fname, String lname, String phone, String photo) {
		this.mID = UUID.randomUUID();
		this.mFirstName = fname;
		this.mLastName = lname;
		this.mPhoneNumber = phone;
		this.mPhoto = photo;		
	}
	public String getFullName() {
		return mFirstName + " " + mLastName;
	}
	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public String getPhoneNumber() {
		return mPhoneNumber;
	}

	public void setPhoneNumber(String mPhoneNumber) {
		this.mPhoneNumber = mPhoneNumber;
	}

	public UUID getID() {
		return mID;
	}

	public String getPhoto() {
		return mPhoto;
	}
	
}
