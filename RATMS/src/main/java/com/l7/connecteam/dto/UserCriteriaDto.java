package com.l7.connecteam.dto;

/**
 * @author soumya.raj
 * Acts as data layer for user criteria relation table
 */
public class UserCriteriaDto {
	private int criteriaID;
	private int userID;
	private int marks;
	private int status;
	public int getCriteriaID() {
		return criteriaID;
	}
	public void setCriteriaID(int criteriaID) {
		this.criteriaID = criteriaID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserCriteriaRel [criteriaID=" + criteriaID + ", userID=" + userID + ", marks=" + marks + ", status="
				+ status + "]";
	}
	
}
