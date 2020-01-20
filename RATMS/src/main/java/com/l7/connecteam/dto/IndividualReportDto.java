package com.l7.connecteam.dto;

public class IndividualReportDto {
	private String trainingGrpName;
	private String assessment;
	private int assessMark;
	private int assessMaxMark;
	private int assessMinMark;
	private String status;
	private int assessPercentage;
	
	public String getTrainingGrpName() {
		return trainingGrpName;
	}
	public void setTrainingGrpName(String trainingGrpName) {
		this.trainingGrpName = trainingGrpName;
	}
	public String getAssessment() {
		return assessment;
	}
	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	public int getAssessMark() {
		return assessMark;
	}
	public void setAssessMark(int assessMark) {
		this.assessMark = assessMark;
	}
	public int getAssessMaxMark() {
		return assessMaxMark;
	}
	public void setAssessMaxMark(int assessMaxMark) {
		this.assessMaxMark = assessMaxMark;
	}
	public int getAssessMinMark() {
		return assessMinMark;
	}
	public void setAssessMinMark(int assessMinMark) {
		this.assessMinMark = assessMinMark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAssessPercentage() {
		return assessPercentage;
	}
	public void setAssessPercentage(int assessPercentage) {
		this.assessPercentage = assessPercentage;
	}
	@Override
	public String toString() {
		return "IndividualReport [trainingGrpName=" + trainingGrpName + ", assessment=" + assessment + ", assessMark="
				+ assessMark + ", assessMaxMark=" + assessMaxMark + ", assessMinMark=" + assessMinMark + ", status="
				+ status + ", assessPercentage=" + assessPercentage + "]";
	}
	
}