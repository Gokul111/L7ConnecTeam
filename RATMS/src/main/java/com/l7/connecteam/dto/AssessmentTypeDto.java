package com.l7.connecteam.dto;

/**
 * @author soumya.raj
 * Data layer for assessment type
 */
public class AssessmentTypeDto {
	private int assessmentTypeID;
	private String assessmentTypeName;
	public int getAssessmentTypeID() {
		return assessmentTypeID;
	}
	public void setAssessmentTypeID(int assessmentTypeID) {
		this.assessmentTypeID = assessmentTypeID;
	}
	public String getAssessmentTypeName() {
		return assessmentTypeName;
	}
	public void setAssessmentTypeName(String assessmentTypeName) {
		this.assessmentTypeName = assessmentTypeName;
	}
	@Override
	public String toString() {
		return "AssessmentTypeDto [assessmentTypeID=" + assessmentTypeID + ", assessmentTypeName=" + assessmentTypeName
				+ "]";
	}
}
