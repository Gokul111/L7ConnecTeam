package com.l7.connecteam.dto;

import java.sql.Date;

public class TrainingGroupDto {
	private int trainGroupID;
	private String trainGroupName;
	private Date trainStartDate;
	private Date trainEndDate;
	private String CoursePlanPath;
	private int typeID;
	private int activeStatus;
	public String getTrainGroupName() {
		return trainGroupName;
	}
	public void setTrainGroupName(String trainGroupName) {
		this.trainGroupName = trainGroupName;
	}
	public int getTrainGroupID() {
		return trainGroupID;
	}
	public void setTrainGroupID(int trainGroupID) {
		this.trainGroupID = trainGroupID;
	}
	public Date getTrainStartDate() {
		return trainStartDate;
	}
	public void setTrainStartDate(Date trainStartDate) {
		this.trainStartDate = trainStartDate;
	}
	public Date getTrainEndDate() {
		return trainEndDate;
	}
	public void setTrainEndDate(Date trainEndDate) {
		this.trainEndDate = trainEndDate;
	}
	public String getCoursePlanPath() {
		return CoursePlanPath;
	}
	public void setCoursePlanPath(String coursePlanPath) {
		CoursePlanPath = coursePlanPath;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	@Override
	public String toString() {
		return "TrainingGroup [trainGroupID=" + trainGroupID + ", trainGroupName=" + trainGroupName
				+ ", trainStartDate=" + trainStartDate + ", trainEndDate=" + trainEndDate + ", CoursePlanPath="
				+ CoursePlanPath + ", typeID=" + typeID + ", activeStatus=" + activeStatus + "]";
	}
	
}
