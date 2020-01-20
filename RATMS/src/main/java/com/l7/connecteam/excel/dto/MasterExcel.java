package com.l7.connecteam.excel.dto;

import java.sql.Date;

/**
 * @author soumya.raj 
 * This class acts as DTO for *_Data.xlsx files
 */
public class MasterExcel {
	private String EmpId;
	private String EmpName;
	private String BatchName;
	private Date BatchStartDate;
	private Date BatchEndDate;
	private String trainingGroupName;
	private String AssessmentName;
	private String AssessmentType;
	private String TechnologyName;
	private int Assessment_minMarks;
	private int Assessment_maxMarks;
	private int Assessment_score;
	private String Assessment_status;

	/**
	 * @return
	 * Returns an occurrence of userName
	 */
	public String getEmpName() {
		return EmpName;
	}

	/**
	 * @param empName
	 * Sets username os a user
	 */
	public void setEmpName(String empName) {
		EmpName = empName;
	}

	/**
	 * @return
	 * Returns an occurrence of batch name
	 */
	public String getBatchName() {
		return BatchName;
	}

	/**
	 * @param batchName
	 * Sets name of a batch
	 */
	public void setBatchName(String batchName) {
		BatchName = batchName;
	}

	/**
	 * @return
	 * Returns an occurrence of batch start date
	 */
	public Date getBatchStartDate() {
		return BatchStartDate;
	}

	/**
	 * @param batchStartDate
	 * Sets start date of a bacth
	 */
	public void setBatchStartDate(Date batchStartDate) {
		BatchStartDate = batchStartDate;
	}

	/**
	 * @return
	 * Returns an occurrence of batch end date
	 */
	public Date getBatchEndDate() {
		return BatchEndDate;
	}

	/**
	 * @param batchEndDate
	 * Sets end date for a batch
	 */
	public void setBatchEndDate(Date batchEndDate) {
		BatchEndDate = batchEndDate;
	}

	/**
	 * @return
	 * returns an occurrence of name of training group
	 */
	public String getTrainingGroupName() {
		return trainingGroupName;
	}

	/**
	 * @param trainingGroupName
	 * Sets the name of a training group
	 */
	public void setTrainingGroupName(String trainingGroupName) {
		this.trainingGroupName = trainingGroupName;
	}

	/**
	 * @return
	 * Returns an occurrence of assessment name
	 */
	public String getAssessmentName() {
		return AssessmentName;
	}

	/**
	 * @param assessmentName
	 * Sets the name of an assessment
	 */
	public void setAssessmentName(String assessmentName) {
		AssessmentName = assessmentName;
	}

	/**
	 * @return
	 * Returns an occurrence of technology name
	 */
	public String getTechnologyName() {
		return TechnologyName;
	}

	/**
	 * @param technologyName
	 * Sets the name for a technology
	 */
	public void setTechnologyName(String technologyName) {
		TechnologyName = technologyName;
	}

	/**
	 * @return
	 * Returns an occurrence of minimum marks of an assessment
	 */
	public int getAssessment_minMarks() {
		return Assessment_minMarks;
	}

	/**
	 * @param assessment_minMarks
	 * Sets minimum marks of an assessment
	 */
	public void setAssessment_minMarks(int assessment_minMarks) {
		Assessment_minMarks = assessment_minMarks;
	}

	/**
	 * @return
	 * Returns an occurrence of maximum marks of an assessment
	 */
	public int getAssessment_maxMarks() {
		return Assessment_maxMarks;
	}

	/**
	 * @param assessment_maxMarks
	 * Sets maximum mark for an assessment
	 */
	public void setAssessment_maxMarks(int assessment_maxMarks) {
		Assessment_maxMarks = assessment_maxMarks;
	}

	/**
	 * @return
	 * Returns the marks of an assessment
	 */
	public int getAssessment_score() {
		return Assessment_score;
	}

	/**
	 * @param assessment_score
	 * Sets the marks of an assessment
	 */
	public void setAssessment_score(int assessment_score) {
		Assessment_score = assessment_score;
	}

	/**
	 * @return
	 * Returns an occurrence of type of an assessment
	 */
	public String getAssessmentType() {
		return AssessmentType;
	}

	/**
	 * @param assessmentType
	 * Sets the type of an assessment
	 */
	public void setAssessmentType(String assessmentType) {
		AssessmentType = assessmentType;
	}

	/**
	 * @return
	 * Returns an occurrence of employee ID of a user
	 */
	public String getEmpId() {
		return EmpId;
	}

	/**
	 * @param empId
	 * Sets the employee ID of a user
	 */
	public void setEmpId(String empId) {
		EmpId = empId;
	}

	/**
	 * @return
	 * Returns an occurrence of assessment status of a user
	 */
	public String getAssessment_status() {
		return Assessment_status;
	}

	/**
	 * @param assessment_status
	 * Sets assessment status of a user
	 */
	public void setAssessment_status(String assessment_status) {
		Assessment_status = assessment_status;
	}

	/**
	 * Prints the content of an instance of this class
	 */
	@Override
	public String toString() {
		return "MasterExcel [EmpId=" + EmpId + ", EmpName=" + EmpName + ", BatchName=" + BatchName + ", BatchStartDate="
				+ BatchStartDate + ", BatchEndDate=" + BatchEndDate + ", trainingGroupName=" + trainingGroupName
				+ ", AssessmentName=" + AssessmentName + ", AssessmentTypeDto=" + AssessmentType + ", TechnologyName="
				+ TechnologyName + ", Assessment_minMarks=" + Assessment_minMarks + ", Assessment_maxMarks="
				+ Assessment_maxMarks + ", Assessment_score=" + Assessment_score + ", Assessment_status="
				+ Assessment_status + "]";
	}

}
