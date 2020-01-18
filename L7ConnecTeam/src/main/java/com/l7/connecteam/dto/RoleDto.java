package com.l7.connecteam.dto;

import java.util.List;

/**
 * @author soumya.raj
 * Data layer for role of a user
 */
public class RoleDto {
	private int roleId;
	private String roleName;
	private int activeStatus;
	private List<FeatureDto> featureList;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	public List<FeatureDto> getFeatureList() {
		return featureList;
	}
	public void setFeatureList(List<FeatureDto> featureList) {
		this.featureList = featureList;
	}
	@Override
	public String toString() {
		return "role [roleId=" + roleId + ", roleName=" + roleName + ", active_status=" + activeStatus
				+ ", featureList=" + featureList + "]";
	}
	
}
