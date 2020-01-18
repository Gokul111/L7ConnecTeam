package com.l7.connecteam.dto;

import java.sql.Date;

/**
 * @author soumya.raj
 * Data layer for assessment
 */
public class AssessmentDto {
	private int assessmentID;
	private String assessmentName;
	private Date creationDate;
	private Date expiryDate;
	private int createdyBy;
	private int assessmentTypeID;
	private int activeStatus;
	public int getAssessmentID() {
		return assessmentID;
	}
	public void setAssessmentID(int assessment_id) {
		this.assessmentID = assessment_id;
	}
	public String getAssessmentName() {
		return assessmentName;
	}
	public void setAssessmentName(String assessment_name) {
		this.assessmentName = assessment_name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getCreatedyBy() {
		return createdyBy;
	}
	public void setCreatedyBy(int createdyBy) {
		this.createdyBy = createdyBy;
	}
	public int getAssessmentTypeID() {
		return assessmentTypeID;
	}
	public void setAssessmentTypeID(int assessmentTypeID) {
		this.assessmentTypeID = assessmentTypeID;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int active_status) {
		this.activeStatus = active_status;
	}
	@Override
	public String toString() {
		return "Assessment [assessmentID=" + assessmentID + ", assessmentName=" + assessmentName + ", creationDate="
				+ creationDate + ", expiryDate=" + expiryDate + ", createdyBy=" + createdyBy + ", assessmentTypeID="
				+ assessmentTypeID + ", activeStatus=" + activeStatus + "]";
	}
	
}
