package com.codeminders.labs.timeextractor.entities;

public class Tip {
	
	private String id;
	private String venueId;
	private String tipText;
	private long createdAt;
	private String userId;
	private String type;
	private String userFirstName;
	private String userLastname;
	private Gender gender;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVenueId() {
		return venueId;
	}
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	public String getTipText() {
		return tipText;
	}
	public void setTipText(String tipText) {
		this.tipText = tipText;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastname() {
		return userLastname;
	}
	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Tip [id=" + id + ", venueId=" + venueId + ", tipText="
				+ tipText + ", createdAt=" + createdAt + ", userId=" + userId
				+ ", type=" + type + ", userFirstName=" + userFirstName
				+ ", userLastname=" + userLastname + ", gender=" + gender + "]";
	}
	
	
	
}
