package com.l7.connecteam.dto;

/**
 * @author soumya.raj
 * Data layer for batchwise assessment report
 *
 */
public class BatchwiseReportDto {
	private String userName;
	private float avgPercentMarks;
	private String trainingGrp;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getavgPercentMarks() {
		return avgPercentMarks;
	}
	public void setavgPercentMarks(float avgPercentMarks) {
		this.avgPercentMarks = avgPercentMarks;
	}
	public String getTrainingGrp() {
		return trainingGrp;
	}
	public void setTrainingGrp(String trainingGrp) {
		this.trainingGrp = trainingGrp;
	}
	@Override
	public String toString() {
		return "BatchwiseReportDto [userName=" + userName + ", avgPercentMarks=" + avgPercentMarks + ", trainingGrp="
				+ trainingGrp + "]";
	}
	
}
