package com.l7.connecteam.dto;

import java.sql.Date;

/**
 * @author Litmus7
 * Acts as data layer for features authorised for a user
 */
public class FeatureDto {
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	private int featureId;
	private String featureName;
	private int moduleId;
	private Date creationDate;
	private int activeStatus;
	private String moduleName;
	public int getFeatureId() {
		return featureId;
	}
	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	@Override
	public String toString() {
		return "FeatureDto [featureId=" + featureId + ", featureName=" + featureName + ", moduleId=" + moduleId
				+ ", creationDate=" + creationDate + ", activeStatus=" + activeStatus + ", moduleName=" + moduleName
				+ "]";
	}
	
}
